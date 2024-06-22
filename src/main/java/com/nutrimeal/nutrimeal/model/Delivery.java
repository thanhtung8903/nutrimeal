package com.nutrimeal.nutrimeal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Integer deliveryId;

    @Column(name = "delivery_date")
    @Temporal(TemporalType.DATE)
    private Date deliveryDate;

    @Column(name = "delivery_status")
    private String deliveryStatus;

    @Column(name = "delivery_note")
    private String deliveryNote;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "shipper_id")
    @JsonIgnore
    private User shipper;

    @Column(name = "delivery_update_time")
    private LocalDateTime deliveryUpdateTime;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "delivery_price")
    private int deliveryPrice;

    @Column(name = "delivery_time")
    private String deliveryTime;

    @Column(name = "delivery_phone")
    private String deliveryPhone;

    @OneToMany(mappedBy = "delivery")
    @JsonIgnore
    private List<DeliveryDetail> deliveryDetails;
}