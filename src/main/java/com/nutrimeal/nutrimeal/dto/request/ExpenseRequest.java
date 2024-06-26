package com.nutrimeal.nutrimeal.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpenseRequest {

    private String ingredientName;
    private int quantity;
    private int unitPrice;
    private String supplier;
    private String purchaseDate;
    private String expirationDate;

}
