package com.nutrimeal.nutrimeal.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class ExpenseRequest {

    private String ingredientName;
    private int quantity;
    private int unitPrice;
    private String supplier;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date purchaseDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expirationDate;

}
