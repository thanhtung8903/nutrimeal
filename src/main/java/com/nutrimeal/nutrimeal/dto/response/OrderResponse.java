package com.nutrimeal.nutrimeal.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderResponse {
    private int orderId;
    private String orderNote;
    private String fullName;
    private String phone;
    private String address;
    private String orderStatus;
    private int orderTotalPrice;
    private String paymentMethod;
    private String deliveryTime;
    private String orderDate;
}
