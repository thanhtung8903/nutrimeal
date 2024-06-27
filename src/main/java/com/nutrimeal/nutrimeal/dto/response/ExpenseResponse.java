package com.nutrimeal.nutrimeal.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ExpenseResponse {

    private int expenseId;
    private String ingredientName;
    private int quantity;
    private int unitPrice;
    private int totalPrice;
    private String supplier;
    private Date purchaseDate;
    private Date expirationDate;
    private Boolean isActive;

}
