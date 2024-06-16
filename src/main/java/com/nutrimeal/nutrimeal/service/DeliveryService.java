package com.nutrimeal.nutrimeal.service;

import com.nutrimeal.nutrimeal.model.*;
import com.nutrimeal.nutrimeal.repository.DailyMenuRepository;
import com.nutrimeal.nutrimeal.repository.DeliveryDetailRepository;
import com.nutrimeal.nutrimeal.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DailyMenuRepository dailyMenuRepository;
    private final DeliveryRepository deliveryRepository;
    private final DeliveryDetailRepository deliveryDetailRepository;

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
//
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
}
