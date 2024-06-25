package com.nutrimeal.nutrimeal.repository;

import com.nutrimeal.nutrimeal.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {

    List<Delivery> findDeliveriesByDeliveryStatus(String deliveryStatus);

    Delivery findByDeliveryId(Integer deliveryId);
}
