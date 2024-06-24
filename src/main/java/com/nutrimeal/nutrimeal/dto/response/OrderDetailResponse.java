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
    private int quantity;
    private int price;
    private String comboName;
    private int comboDay;
}
