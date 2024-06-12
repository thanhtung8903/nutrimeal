package com.nutrimeal.nutrimeal.controller.order;

import com.nutrimeal.nutrimeal.dto.request.OrderRequest;
import com.nutrimeal.nutrimeal.model.*;
import com.nutrimeal.nutrimeal.service.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.*;

@RequiredArgsConstructor
@RestController
public class OrderRestController {

    private final OrderService orderService;
    private final UserService userService;
    private final OrderDetailService orderDetailService;
    private final OrderBasketService orderBasketService;
    private final PaymentMethodService paymentMethodService;
    private final DeliveryTimeService deliveryTimeService;
    private final AddressService addressService;
    private final VNPayService vnPayService;
    private final PromotionService promotionService;
    private final UserPromotionService userPromotionService;

    @PostMapping("/order/create")
    public String createOrder(@RequestBody OrderRequest orderRequest, Principal principal) {
        if (principal == null) {
            return "Bạn cần đăng nhập để cập nhật số lượng";
        }
        User user;
        if (principal instanceof OAuth2AuthenticationToken token) {
            OAuth2User oauthUser = token.getPrincipal();
            user = userService.findByEmail(oauthUser.getAttribute("email"));
        } else {
            user = userService.findByUsername(principal.getName());
        }

        Order order = new Order();
        order.setOrderTempPrice(orderRequest.getOrderTempPrice());
        order.setOrderDeliveryPrice(orderRequest.getOrderDeliveryPrice());
        order.setOrderDiscount(orderRequest.getOrderDiscount());
        order.setOrderTotalPrice(orderRequest.getOrderTotalPrice());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setOrderNote(orderRequest.getOrderNote());
        order.setPaymentMethod(paymentMethodService.findById(orderRequest.getPaymentMethodId()));
        order.setDeliveryTime(deliveryTimeService.findById(orderRequest.getDeliveryTimeId()));
        order.setOrderDate(new Date());
        order.setUser(user);
        order.setAddress(addressService.findById(orderRequest.getAddressId()));

        // update quantity voucher and flag used
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


        List<OrderBasket> orderBaskets = orderBasketService.findAllByUser(user);

        orderService.save(order);

        for (OrderBasket orderBasket : orderBaskets) {
            OrderDetail orderDetail = getOrderDetail(orderBasket);
            orderDetail.setOrder(order);
            orderDetailService.save(orderDetail);
            orderBasketService.delete(orderBasket);
        }

        return order.getOrderId() + "";
    }

    private static OrderDetail getOrderDetail(OrderBasket orderBasket) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setCombo(orderBasket.getCombo());
        orderDetail.setOrderDetailQuantity(orderBasket.getQuantity());
        if (orderBasket.getDay() == 7) {
            orderDetail.setOrderDetailPrice(orderBasket.getSubTotal7Days());
            orderDetail.setComboDay(7);
        } else {
            orderDetail.setOrderDetailPrice(orderBasket.getSubTotal30Days());
            orderDetail.setComboDay(30);
        }
        return orderDetail;
    }

    @PostMapping("/order/createvnpay")
    public ResponseEntity<?> submitOrder(@RequestBody OrderRequest orderRequest,
                                         HttpServletRequest request, Principal principal, Model model) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
        User user;
        if (principal instanceof OAuth2AuthenticationToken && principal != null) {
            boolean isOauth2User = principal instanceof OAuth2AuthenticationToken && principal != null;
            model.addAttribute("isOauth2User", isOauth2User);
            OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) principal;
            OAuth2User oauthUser = token.getPrincipal();
            user = userService.findByEmail(oauthUser.getAttribute("email"));
        } else {
            model.addAttribute("isOauth2User", false);
            user = userService.findByUsername(principal.getName());
        }
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String vnpayUrl = vnPayService.createOrder(orderRequest, baseUrl, user);
        Map<String, String> response = new HashMap<>();
        response.put("vnPayUrl", vnpayUrl);
        return ResponseEntity.ok(response);
    }
}
