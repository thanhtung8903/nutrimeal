package com.nutrimeal.nutrimeal.repository;

import com.nutrimeal.nutrimeal.model.Faq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaqRepository extends JpaRepository<Faq, Integer> {
}