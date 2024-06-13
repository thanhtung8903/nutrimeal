package com.nutrimeal.nutrimeal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "user_promotion")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserPromotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_promotion_id")
    private int userPromotionId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "promotion_id")
    private int promotionId;


}
