package com.nutrimeal.nutrimeal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "combo_type")
public class ComboType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "combo_type_id")
    private int comboTypeId;

    @Column(name = "combo_type_name")
    private String comboTypeName;

    @Column(name = "combo_type_description")
    private String comboTypeDescription;
}