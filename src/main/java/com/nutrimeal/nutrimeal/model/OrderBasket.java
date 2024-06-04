package com.nutrimeal.nutrimeal.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "order_basket")
public class OrderBasket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_basket_id")
    private int orderBasketId;

    @Column(name = "order_basket_quantity")
    private int quantity;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "combo_id")
    private Combo combo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Transient
    public int getSubTotal7Days() {
        return this.quantity * this.combo.getComboPrice7Days();
    }

    @Transient
    public int getSubTotal30Days() {
        return this.quantity * this.combo.getComboPrice30Days();
    }
}