package com.nutrimeal.nutrimeal.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "combo")
public class Combo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "combo_id")
    private int comboId;

    @Column(name = "combo_name")
    private String comboName;

    @Column(name = "combo_description")
    private String comboDescription;

    @Column(name = "combo_image")
    private String comboImage;

    @Column(name = "combo_price_7days")
    private int comboPrice7Days;

    @Column(name = "combo_price_30days")
    private int comboPrice30Days;

    @Column(name = "combo_calories")
    private int comboCalories;

    @ManyToOne
    @JoinColumn(name = "combo_type_id")
    private ComboType comboType;

    @Column(name = "combo_time")
    private String comboTime;

    @Column(name = "is_active")
    private Boolean isActive;
}