package com.nutrimeal.nutrimeal.service;

import com.nutrimeal.nutrimeal.dto.response.DeliveryDetailResponse;
import com.nutrimeal.nutrimeal.dto.response.DeliveryResponse;
import com.nutrimeal.nutrimeal.model.*;
import com.nutrimeal.nutrimeal.repository.DailyMenuRepository;
import com.nutrimeal.nutrimeal.repository.DeliveryDetailRepository;
import com.nutrimeal.nutrimeal.repository.DeliveryRepository;
import com.nutrimeal.nutrimeal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DailyMenuRepository dailyMenuRepository;
    private final DeliveryRepository deliveryRepository;
    private final DeliveryDetailRepository deliveryDetailRepository;
    private final UserRepository userRepository;

    @Transactional
    public void createDelivery(Order order) {
        List<OrderDetail> orderDetails = order.getOrderDetails();
        Date date = order.getOrderDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = 7;
        List<OrderDetail> orderDetailList = order.getOrderDetails();
        for (OrderDetail orderDetail : orderDetails) {
            if (orderDetail.getComboDay() == 30) {
                day = 30;
                break;
            }
        }
        for (int i = 1; i <= day; i++) {
            calendar.add(Calendar.DATE, 1);
            Date deliveryDate = calendar.getTime();
            Delivery delivery = new Delivery();
            delivery.setDeliveryDate(deliveryDate);
            delivery.setDeliveryStatus(DeliveryStatus.NOT_DELIVERED.toString());
            delivery.setDeliveryAddress(order.getAddress().getFullAddress());
            delivery.setDeliveryTime(order.getDeliveryTime().getDeliveryTime());
            delivery.setDeliveryPhone(order.getAddress().getPhone());
            delivery.setOrder(order);
            delivery.setUser(order.getUser());
            delivery.setDeliveryUpdateTime(null);
            delivery.setDeliveryNote(null);
            if (order.getPaymentMethod().getPaymentMethodId() == 1) {
                if (i == 1) {
                    delivery.setDeliveryPrice(order.getOrderTotalPrice());
                }
            } else {
                delivery.setDeliveryPrice(0);
            }

            String district = order.getAddress().getDistrict();
            switch (district) {
                case "Quận Long Biên":
                    delivery.setShipper(userRepository.findAllShipper().stream().filter(user -> user.getUsername().equals("shipper1")).findFirst().orElse(null));
                    break;
                case "Quận Hoàn Kiếm":
                    delivery.setShipper(userRepository.findAllShipper().stream().filter(user -> user.getUsername().equals("shipper2")).findFirst().orElse(null));
                    break;
                case "Quận Hai Bà Trưng":
                    delivery.setShipper(userRepository.findAllShipper().stream().filter(user -> user.getUsername().equals("shipper3")).findFirst().orElse(null));
                    break;
                case "Quận Đống Đa":
                    delivery.setShipper(userRepository.findAllShipper().stream().filter(user -> user.getUsername().equals("shipper4")).findFirst().orElse(null));
                    break;
                default:
                    delivery.setShipper(null);
            }

            deliveryRepository.save(delivery);

            int countDay = i;

            createDeliveryDetails(delivery, orderDetailList, countDay);
        }
    }

    public DailyMenu getDailyMenu(Date date, String type) {
        return dailyMenuRepository.findByDailyMenuDateAndDailyMenuTypeAndIsActiveTrue(date, type);
    }

    @Transactional
    public void createDeliveryDetails(Delivery delivery, List<OrderDetail> orderDetails, int countDay) {
        Date date = delivery.getDeliveryDate();
        for (OrderDetail orderDetail : orderDetails) {
            if (countDay >= 8 && orderDetail.getComboDay() == 7) {
                continue;
            }


            DeliveryDetail deliveryDetail = new DeliveryDetail();
            deliveryDetail.setDelivery(delivery);
            DailyMenu dailyMenu = getDailyMenu(date, orderDetail.getCombo().getComboType().getComboTypeName());

            // if daily menu is not found, skip this order detail
            if (dailyMenu == null) {
                continue;
            }

            String comboTime = orderDetail.getCombo().getComboTime();
            boolean isBreakfast = comboTime.contains("B");
            boolean isLunch = comboTime.contains("L");
            boolean isDinner = comboTime.contains("D");

            if (isBreakfast) {
                deliveryDetail.setDishBreakfast(dailyMenu.getDishBreakfast());
            }

            if (isLunch) {
                deliveryDetail.setDishLunch(dailyMenu.getDishLunch());
            }

            if (isDinner) {
                deliveryDetail.setDishDinner(dailyMenu.getDishDinner());
            }

            deliveryDetail.setDeliveryDetailQuantity(orderDetail.getOrderDetailQuantity());

            deliveryDetail.setDeliveryDetailCombo(orderDetail.getCombo().getComboName());

            deliveryDetailRepository.save(deliveryDetail);
        }

    }

    public List<DeliveryResponse> findDeliveriesByDeliveryStatus(String deliveryStatus) {
        List<Delivery> deliveryList = deliveryRepository.findDeliveriesByDeliveryStatus(deliveryStatus);
        List<DeliveryResponse> deliveryResponseList = new ArrayList<>();
        for (Delivery delivery : deliveryList) {
            DeliveryResponse deliveryResponse = new DeliveryResponse();
            deliveryResponse.setDeliveryId(delivery.getDeliveryId());
            deliveryResponse.setDeliveryStatus(delivery.getDeliveryStatus());
            deliveryResponse.setDeliveryTime(delivery.getDeliveryTime());
            deliveryResponse.setDeliveryDate(delivery.getDeliveryDate().toString());
            deliveryResponse.setDeliveryNote(delivery.getDeliveryNote());
            deliveryResponse.setDeliveryAddress(delivery.getDeliveryAddress());
            deliveryResponse.setDeliveryPhone(delivery.getDeliveryPhone());
            deliveryResponse.setCustomerFullName(delivery.getOrder().getUser().getFullName());
            deliveryResponse.setShipperFullName(delivery.getShipper().getFullName());
//            deliveryResponse.setDeliveryUpdateTime(delivery.getDeliveryUpdateTime().toString());
            deliveryResponse.setDeliveryPrice(delivery.getDeliveryPrice());
            deliveryResponseList.add(deliveryResponse);
        }

        return deliveryResponseList;
    }

    public void updateDeliveryStatus(Integer deliveryId, String shipperId) {
        Delivery delivery = deliveryRepository.findByDeliveryId(deliveryId);
        User shipper = userRepository.findByUserId(shipperId);

        delivery.setShipper(shipper);
        deliveryRepository.save(delivery);
    }

    public DeliveryDetailResponse findDeliveryDetail(Integer deliveryId) {
        Delivery delivery = deliveryRepository.findByDeliveryId(deliveryId);
        DeliveryDetailResponse deliveryDetailResponse = new DeliveryDetailResponse();

        DeliveryResponse deliveryResponse = new DeliveryResponse();
        deliveryResponse.setDeliveryId(delivery.getDeliveryId());
        deliveryResponse.setDeliveryStatus(delivery.getDeliveryStatus());
        deliveryResponse.setDeliveryTime(delivery.getDeliveryTime());
        deliveryResponse.setDeliveryDate(delivery.getDeliveryDate().toString());
        deliveryResponse.setDeliveryNote(delivery.getDeliveryNote());
        deliveryResponse.setDeliveryAddress(delivery.getDeliveryAddress());
        deliveryResponse.setDeliveryPhone(delivery.getDeliveryPhone());
        deliveryResponse.setCustomerFullName(delivery.getOrder().getUser().getFullName());
        deliveryResponse.setShipperFullName(delivery.getShipper().getFullName());
        deliveryResponse.setDeliveryPrice(delivery.getDeliveryPrice());
//        deliveryDetailResponse.setDelivery(deliveryResponse);

        List<DeliveryDetail> deliveryDetails = deliveryDetailRepository.findDeliveryDetailsByDelivery(delivery);

        deliveryDetailResponse.setDeliveryDetails(deliveryDetails);
//        deliveryDetailResponse.setDelivery(deliveryResponse);

        return deliveryDetailResponse;
    }

    public List<DeliveryResponse> findDeliveriesByShipperAndStatus(User shipper, String status) {
        List<Delivery> deliveryList = deliveryRepository.findDeliveriesByShipperAndDeliveryStatus(shipper, status);
        List<DeliveryResponse> deliveryResponseList = new ArrayList<>();
        for (Delivery delivery : deliveryList) {
            DeliveryResponse deliveryResponse = new DeliveryResponse();
            deliveryResponse.setDeliveryId(delivery.getDeliveryId());
            deliveryResponse.setDeliveryStatus(delivery.getDeliveryStatus());
            deliveryResponse.setDeliveryTime(delivery.getDeliveryTime());
            deliveryResponse.setDeliveryDate(delivery.getDeliveryDate().toString());
            deliveryResponse.setDeliveryNote(delivery.getDeliveryNote());
            deliveryResponse.setDeliveryAddress(delivery.getDeliveryAddress());
            deliveryResponse.setDeliveryPhone(delivery.getDeliveryPhone());
            deliveryResponse.setCustomerFullName(delivery.getOrder().getUser().getFullName());
            deliveryResponse.setShipperFullName(delivery.getShipper().getFullName());
//            deliveryResponse.setDeliveryUpdateTime(delivery.getDeliveryUpdateTime().toString());
            deliveryResponse.setDeliveryPrice(delivery.getDeliveryPrice());
            deliveryResponseList.add(deliveryResponse);
    }
        return deliveryResponseList;
    }

    public DeliveryResponse updateDeliveryStatus(int deliveryId, String status, User shipper, String note) {
        Delivery delivery = deliveryRepository.findByDeliveryIdAndShipper(deliveryId, shipper);
        delivery.setDeliveryStatus(status);
        delivery.setDeliveryNote(note);
        delivery.setDeliveryUpdateTime(LocalDateTime.now());
        deliveryRepository.save(delivery);

        DeliveryResponse deliveryResponse = new DeliveryResponse();
        deliveryResponse.setDeliveryId(delivery.getDeliveryId());
        deliveryResponse.setDeliveryStatus(delivery.getDeliveryStatus());
        deliveryResponse.setDeliveryTime(delivery.getDeliveryTime());
        deliveryResponse.setDeliveryDate(delivery.getDeliveryDate().toString());
        deliveryResponse.setDeliveryNote(delivery.getDeliveryNote());
        deliveryResponse.setDeliveryAddress(delivery.getDeliveryAddress());
        deliveryResponse.setDeliveryPhone(delivery.getDeliveryPhone());
        deliveryResponse.setCustomerFullName(delivery.getOrder().getUser().getFullName());
        deliveryResponse.setShipperFullName(delivery.getShipper().getFullName());
        deliveryResponse.setDeliveryUpdateTime(LocalDateTime.now().toString());
        return deliveryResponse;
    }
}
