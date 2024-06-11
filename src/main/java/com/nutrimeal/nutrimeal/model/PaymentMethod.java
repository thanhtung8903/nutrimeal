package com.nutrimeal.nutrimeal.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "payment_method")
public class PaymentMethod {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "payment_method_id")
        private int paymentMethodId;

        @Column(name = "payment_method_name")
        private String paymentMethodName;

        @Column(name = "payment_method_description")
        private String paymentMethodDescription;

        @Column(name = "is_active")
        private Boolean isActive;
}
