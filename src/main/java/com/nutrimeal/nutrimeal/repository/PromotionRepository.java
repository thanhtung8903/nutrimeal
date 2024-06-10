package com.nutrimeal.nutrimeal.repository;

import com.nutrimeal.nutrimeal.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Integer> {

    Optional<Promotion> findByPromotionCode(String promotionCode);
}
