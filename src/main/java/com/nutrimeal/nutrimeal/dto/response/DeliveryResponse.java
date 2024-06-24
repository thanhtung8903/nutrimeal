package com.nutrimeal.nutrimeal.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Data
@NoArgsConstructor
public class DeliveryResponse {

    private int deliveryId;
    private String deliveryStatus;
    private String deliveryTime;
    private String deliveryDate;
    private String deliveryNote;
    private String deliveryAddress;
    private String deliveryPhone;
    private String customerFullName;
    private String shipperFullName;
    private String deliveryUpdateTime;
    private int deliveryPrice;

}
