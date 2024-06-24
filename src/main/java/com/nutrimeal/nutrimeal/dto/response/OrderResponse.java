package com.nutrimeal.nutrimeal.dto.response;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
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
