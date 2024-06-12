package com.nutrimeal.nutrimeal.service;

import com.nutrimeal.nutrimeal.model.UserPromotion;
import com.nutrimeal.nutrimeal.repository.UserPromotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPromotionService {
    private final UserPromotionRepository userPromotionRepository;

    public boolean existsByUserIdAndPromotionId(String userId, int promotionId) {
        return userPromotionRepository.existsByUserIdAndPromotionId(userId, promotionId);
    }

    public void save(UserPromotion userPromotion) {
        userPromotionRepository.save(userPromotion);
    }
}
