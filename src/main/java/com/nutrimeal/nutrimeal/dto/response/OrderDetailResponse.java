package com.nutrimeal.nutrimeal.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class OrderDetailResponse {
    private int id;
    private String comboName;
    private String comboImage;
    private String comboDay;
    private int price;
    private int quantity;
}
