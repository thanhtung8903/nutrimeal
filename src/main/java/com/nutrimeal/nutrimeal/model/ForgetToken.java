package com.nutrimeal.nutrimeal.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "forget_token")
public class ForgetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "forget_token_id")
    private Integer forgetTokenId;

    @Column(name = "forget_token", length = 255, nullable = false)
    private String forgetToken;

    @Column(name = "forget_token_created", nullable = false)
    private LocalDateTime forgetTokenCreated;

    @Column(name = "forget_token_expired", nullable = false)
    private LocalDateTime forgetTokenExpired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
