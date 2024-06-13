package com.nutrimeal.nutrimeal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "promotion")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promotion_id")
    private int promotionId;

    @Column(name = "promotion_code")
    private String promotionCode;

    @Column(name = "promotion_quantity")
    private int promotionQuantity;

    @Column(name = "promotion_status")
    private Boolean promotionStatus;

    @Column(name = "promotion_description")
    private String promotionDescription;

    @Column(name = "promotion_discount")
    private int promotionDiscount;
}