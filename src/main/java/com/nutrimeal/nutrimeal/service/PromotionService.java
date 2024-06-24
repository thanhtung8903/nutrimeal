package com.nutrimeal.nutrimeal.service;

import com.nutrimeal.nutrimeal.model.Promotion;
import com.nutrimeal.nutrimeal.repository.PromotionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Promotion> getAllPromotions(Pageable pageable) {
        return promotionRepository.findAllPromotion(pageable);
    }

    public Page<Promotion> findAllByPromotionCode(String promotionCode, Pageable pageable) {
        return promotionRepository.findAllByPromotionCode(promotionCode, pageable);
    }

    public Promotion findPromotionById(Integer id) {
        return promotionRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deletePromotionById(Integer id) {
        promotionRepository.deletePromotionById(id);
    }

    public void updatePromotion(Integer id, Promotion promotion) {
        Promotion oldPromotion = promotionRepository.findById(id).orElseThrow();
        oldPromotion.setPromotionCode(promotion.getPromotionCode());
        oldPromotion.setPromotionDescription(promotion.getPromotionDescription());
        oldPromotion.setPromotionDiscount(promotion.getPromotionDiscount());
        oldPromotion.setPromotionQuantity(promotion.getPromotionQuantity());
        promotionRepository.save(oldPromotion);
    }

    public boolean existsByPromotionCode(String promotionCode) {
        return promotionRepository.existsByPromotionCode(promotionCode);
    }


}
