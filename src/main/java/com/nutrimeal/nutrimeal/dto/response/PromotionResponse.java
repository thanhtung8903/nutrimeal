package com.nutrimeal.nutrimeal.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class PromotionResponse {

    private String promotionId;
    private String promotionCode;
    private int quantity;
    private boolean status;
    private String promotionDescription;
    private int discount;

}
