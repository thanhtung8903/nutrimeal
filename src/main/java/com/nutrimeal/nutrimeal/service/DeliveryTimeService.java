package com.nutrimeal.nutrimeal.service;

import com.nutrimeal.nutrimeal.model.DeliveryTime;
import com.nutrimeal.nutrimeal.repository.DeliveryTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryTimeService {
    private final DeliveryTimeRepository deliveryTimeRepository;

    public List<DeliveryTime> getAllDeliveryTimes() {
        return deliveryTimeRepository.findAll();
    }

    public DeliveryTime findById(int id) {
        return deliveryTimeRepository.findById(id).orElse(null);
    }
}
