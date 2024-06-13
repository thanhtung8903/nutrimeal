package com.nutrimeal.nutrimeal.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "delivery_time")
public class DeliveryTime {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "delivery_time_id")
        private int deliveryTimeId;

        @Column(name = "delivery_time")
        private String deliveryTime;

        @Column(name = "is_active")
        private Boolean isActive;
}
