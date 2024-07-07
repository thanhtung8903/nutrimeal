package com.nutrimeal.nutrimeal.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Data
@Table(name = "point_history")
public class PointHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pointHistoryId;

    @Column(name = "point_history_date")
    private LocalDate pointHistoryDate;

    @Column(name = "point_history_description")
    private String pointHistoryDescription;

    @Column(name = "point_history_point")
    private int pointHistoryPoint;

    @Column(name = "point_history_status")
    private String pointHistoryStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
