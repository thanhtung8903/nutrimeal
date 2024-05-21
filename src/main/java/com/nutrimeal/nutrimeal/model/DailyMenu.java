package com.nutrimeal.nutrimeal.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
@Table(name = "daily_menu")
public class DailyMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "daily_menu_id")
    private int dailyMenuId;

    @Column(name = "daily_menu_day")
    @Temporal(TemporalType.DATE)
    private Date dailyMenuDay;

    @Enumerated(EnumType.STRING)
    @Column(name = "daily_menu_meal_time")
    private MealTime dailyMenuMealTime;


}