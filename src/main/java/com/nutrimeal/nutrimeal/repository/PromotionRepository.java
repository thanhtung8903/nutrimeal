package com.nutrimeal.nutrimeal.repository;

import com.nutrimeal.nutrimeal.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Integer> {
}
