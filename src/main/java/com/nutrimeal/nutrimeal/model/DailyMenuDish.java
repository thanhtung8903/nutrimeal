package com.nutrimeal.nutrimeal.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "daily_menu_dish")
public class DailyMenuDish {

    @Id
    @ManyToOne
    @JoinColumn(name = "daily_menu_id")
    private DailyMenu dailyMenu;

    @Id
    @ManyToOne
    @JoinColumn(name = "dish_id")
    private Dish dish;

    @Column(name = "menu_type")
    private String menuType;
}