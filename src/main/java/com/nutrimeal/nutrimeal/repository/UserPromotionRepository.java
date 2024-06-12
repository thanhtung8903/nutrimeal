package com.nutrimeal.nutrimeal.repository;

import com.nutrimeal.nutrimeal.model.UserPromotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPromotionRepository extends JpaRepository<UserPromotion, Integer> {


    public boolean existsByUserIdAndPromotionId(String userId, int promotionId);
}
