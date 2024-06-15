package com.nutrimeal.nutrimeal.service;

import com.nutrimeal.nutrimeal.model.Promotion;
import com.nutrimeal.nutrimeal.repository.PromotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PromotionService {

    private final PromotionRepository promotionRepository;

    public Page<Promotion> getAllPromotions(Pageable pageable) {
        return promotionRepository.findAllPromotion(pageable);
    }

    public void save(Promotion promotion) {
        promotionRepository.save(promotion);
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
        promotionRepository.save(oldPromotion);
    }

    public Optional<Promotion> findByPromotionCode(String code) {
        return promotionRepository.findByPromotionCode(code);
    }
}
