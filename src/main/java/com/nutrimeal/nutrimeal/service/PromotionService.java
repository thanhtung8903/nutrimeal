package com.nutrimeal.nutrimeal.service;

import com.nutrimeal.nutrimeal.model.Promotion;
import com.nutrimeal.nutrimeal.repository.PromotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PromotionService {

    private final PromotionRepository promotionRepository;

    public Optional<Promotion> findByPromotionCode(String promotionCode) {
        return promotionRepository.findByPromotionCode(promotionCode);
    }

    public void save(Promotion promotion) {
        promotionRepository.save(promotion);
    }
}
