package com.nutrimeal.nutrimeal.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "daily_menu")
public class DailyMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "daily_menu_id")
    private int dailyMenuId;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "daily_menu_date")
    private Date dailyMenuDate;

    @Column(name = "daily_menu_type")
    private String dailyMenuType;

    @ManyToOne
    @JoinColumn(name = "dish_id_breakfast")
    private Dish dishBreakfast;

    @ManyToOne
    @JoinColumn(name = "dish_id_lunch")
    private Dish dishLunch;

    @ManyToOne
    @JoinColumn(name = "dish_id_dinner")
    private Dish dishDinner;

    @Column(name = "is_active")
    private Boolean isActive;
}