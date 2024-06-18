package com.nutrimeal.nutrimeal.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "delivery_detail")
@Data
public class DeliveryDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_detail_id")
    private int deliveryDetailId;

    @ManyToOne
    @JoinColumn(name = "delivery_id", nullable = false)
    private Delivery delivery;

    @ManyToOne
    @JoinColumn(name = "dish_breakfast_id")
    private Dish dishBreakfast;

    @ManyToOne
    @JoinColumn(name = "dish_lunch_id")
    private Dish dishLunch;

    @ManyToOne
    @JoinColumn(name = "dish_dinner_id")
    private Dish dishDinner;

    @Column(name = "delivery_detail_quantity", nullable = false)
    private int deliveryDetailQuantity;

    @Column(name = "delivery_detail_combo")
    private String deliveryDetailCombo;

}
