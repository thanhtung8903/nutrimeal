package com.nutrimeal.nutrimeal.controller.api;

import com.nutrimeal.nutrimeal.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/promotion")
@RequiredArgsConstructor
public class RestPromotion {

    private final PromotionService promotionService;

    @GetMapping
    public ResponseEntity<?> getAllPromotion(@RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 12);
        return ResponseEntity.ok(promotionService.getAllPromotions(pageable));
    }

}
