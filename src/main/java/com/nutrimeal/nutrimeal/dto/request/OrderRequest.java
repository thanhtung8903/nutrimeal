package com.nutrimeal.nutrimeal.dto.request;

import lombok.Data;

@Data
public class OrderRequest {
    private int addressId;
    private int orderTempPrice;
    private int orderDeliveryPrice;
    private int orderDiscount;
    private int orderTotalPrice;
    private int paymentMethodId;
    private int deliveryTimeId;
    private String orderNote;
    private String promotionCode;
    private int pointsDiscount;
}
