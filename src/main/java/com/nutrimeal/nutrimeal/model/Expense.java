package com.nutrimeal.nutrimeal.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Data
@Table(name = "expense")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_id")
    private int expenseId;

    @Column(name = "ingredient_name")
    private String ingredientName;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "unit_price")
    private int unitPrice;

    @Column(name = "supplier")
    private String supplier;

    @Column(name = "purchase_date")
    private String purchaseDate;

    @Column(name = "expiration_date")
    private String expirationDate;

    @Column(name = "is_active")
    private Boolean isActive;
}
