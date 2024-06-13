package com.nutrimeal.nutrimeal.service;

import com.nutrimeal.nutrimeal.model.PaymentMethod;
import com.nutrimeal.nutrimeal.repository.PaymentMethodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    public List<PaymentMethod> getAllPaymentMethods() {
        return paymentMethodRepository.findAll();
    }

    public PaymentMethod findById(int id) {
        return paymentMethodRepository.findById(id).orElse(null);
    }
}
