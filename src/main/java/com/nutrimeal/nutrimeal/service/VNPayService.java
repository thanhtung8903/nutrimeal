package com.nutrimeal.nutrimeal.service;

import com.nutrimeal.nutrimeal.config.VNPayConfig;
import com.nutrimeal.nutrimeal.dto.request.OrderRequest;
import com.nutrimeal.nutrimeal.model.*;
import com.nutrimeal.nutrimeal.repository.OrderRepository;
import com.nutrimeal.nutrimeal.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class VNPayService {

    private final UserRepository userRepository;
    private final OrderRepository ordersRepository;
    private final PaymentMethodService paymentMethodService;
    private final AddressService addressService;
    private final DeliveryTimeService deliveryTimeService;
    private final OrderBasketService orderBasketService;
    private final OrderDetailService orderDetailService;
    private final PromotionService promotionService;
    private final UserPromotionService userPromotionService;

    public String createOrder(OrderRequest orderRequest, String urlReturn, User user) {

        Order orders = new Order();
        orders.setUser(user);
        orders.setOrderTotalPrice(orderRequest.getOrderTotalPrice());
        orders.setOrderDate(new Date());
        orders.setPaymentMethod(paymentMethodService.findById(orderRequest.getPaymentMethodId()));
        orders.setOrderNote(orderRequest.getOrderNote());
        orders.setAddress(addressService.findById(orderRequest.getAddressId()));
        orders.setOrderDiscount(orderRequest.getOrderDiscount());
        orders.setDeliveryTime(deliveryTimeService.findById(orderRequest.getDeliveryTimeId()));
        orders.setOrderDeliveryPrice(orderRequest.getOrderDeliveryPrice());
        orders.setOrderTempPrice(orderRequest.getOrderTempPrice());
        orders.setOrderStatus(OrderStatus.PENDING);

        if (orderRequest.getPromotionCode() != null) {
            Optional<Promotion> promotion = promotionService.findByPromotionCode(orderRequest.getPromotionCode());
            if (promotion.isPresent()) {
                UserPromotion userPromotion = new UserPromotion();
                userPromotion.setUserId(user.getUserId());
                userPromotion.setPromotionId(promotion.get().getPromotionId());
                userPromotionService.save(userPromotion);
                promotion.get().setPromotionQuantity(promotion.get().getPromotionQuantity() - 1);
                promotionService.save(promotion.get());
            }
        }

        ordersRepository.save(orders);

        List<OrderBasket> orderBaskets = orderBasketService.findAllByUser(user);

        for (OrderBasket orderBasket : orderBaskets) {
            OrderDetail orderDetail = getOrderDetail(orderBasket);
            orderDetail.setOrder(orders);
            orderDetailService.save(orderDetail);
            orderBasketService.delete(orderBasket);
        }

        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String vnp_TxnRef = VNPayConfig.getRandomNumber(8);
        String vnp_IpAddr = "127.0.0.1";
        String vnp_TmnCode = VNPayConfig.vnp_TmnCode;
        String orderType = "order-type";

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(orders.getOrderTotalPrice() * 100));
        vnp_Params.put("vnp_CurrCode", "VND");

        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", String.valueOf(orders.getOrderId()));
        vnp_Params.put("vnp_OrderType", orderType);

        String locate = "vn";
        vnp_Params.put("vnp_Locale", locate);

        urlReturn += VNPayConfig.vnp_Returnurl;
        vnp_Params.put("vnp_ReturnUrl", urlReturn);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                try {
                    hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    //Build query
                    query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                    query.append('=');
                    query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = VNPayConfig.hmacSHA512(VNPayConfig.vnp_HashSecret, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = VNPayConfig.vnp_PayUrl + "?" + queryUrl;
        return paymentUrl;
    }

    public int orderReturn(HttpServletRequest request, User userInfo) {
        Map fields = new HashMap();
        for (Enumeration params = request.getParameterNames(); params.hasMoreElements(); ) {
            String fieldName = null;
            String fieldValue = null;
            try {
                fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
                fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                fields.put(fieldName, fieldValue);
            }
        }

        String vnp_SecureHash = request.getParameter("vnp_SecureHash");
        if (fields.containsKey("vnp_SecureHashType")) {
            fields.remove("vnp_SecureHashType");
        }
        if (fields.containsKey("vnp_SecureHash")) {
            fields.remove("vnp_SecureHash");
        }

        String signValue = VNPayConfig.hashAllFields(fields);
        User user = userRepository.findByEmail(userInfo.getEmail()).orElseThrow();
        String orderInfo = request.getParameter("vnp_OrderInfo");
        Order order = ordersRepository.findByUserAndOrderId(user, Integer.parseInt(orderInfo));
        if (signValue.equals(vnp_SecureHash)) {
            if ("00".equals(request.getParameter("vnp_TransactionStatus"))) {
                order.setOrderStatus(OrderStatus.PROCESSING);
                ordersRepository.save(order);
                return order.getOrderId();
            } else {
                order.setOrderStatus(OrderStatus.CANCELLED);
                ordersRepository.save(order);
                return order.getOrderId();
            }
        } else {
            order.setOrderStatus(OrderStatus.CANCELLED);
            ordersRepository.save(order);
            return order.getOrderId();
        }
    }

    private static OrderDetail getOrderDetail(OrderBasket orderBasket) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderDetailQuantity(orderBasket.getQuantity());
        orderDetail.setComboDay(orderBasket.getDay());
        orderDetail.setCombo(orderBasket.getCombo());
        if(orderBasket.getDay() == 7){
            orderDetail.setOrderDetailPrice(orderBasket.getSubTotal7Days());
        }else{
            orderDetail.setOrderDetailPrice(orderBasket.getSubTotal30Days());
        }
        return orderDetail;
    }
}
