package com.nutrimeal.nutrimeal.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Data
@Table(name = "faq")
public class Faq {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int faqId;

    @Column(name = "question")
    private String question;

    @Column(name = "answer")
    private String answer;
}
