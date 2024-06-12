package com.nutrimeal.nutrimeal.repository;

import com.nutrimeal.nutrimeal.model.Dish;
import com.nutrimeal.nutrimeal.model.Promotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Integer> {

    Optional<Promotion> findByPromotionCode(String promotionCode);

    @Query("SELECT p from  Promotion p where p.promotionStatus = true ")
    Page<Promotion> findAllPromotion(Pageable pageable);

    @Query("SELECT p FROM Promotion p WHERE p.promotionCode LIKE %:promotionCode% AND p.promotionStatus = true")
    Page<Promotion> findAllByPromotionCode(@Param("promotionCode") String promotionCode, Pageable pageable);

    // set promotion status to false
    @Modifying
    @Query("UPDATE Promotion p SET p.promotionStatus = false WHERE p.promotionId = :id")
    void deletePromotionById(@Param("id") Integer id);

}
