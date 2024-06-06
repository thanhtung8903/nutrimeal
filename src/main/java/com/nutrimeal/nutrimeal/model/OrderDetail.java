package com.nutrimeal.nutrimeal.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "order_detail")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private int orderDetailId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "order_detail_quantity")
    private int orderDetailQuantity;

    @Column(name = "order_detail_price")
    private int orderDetailPrice;

    @ManyToOne
    @JoinColumn(name = "combo_id")
    private Combo combo;
}