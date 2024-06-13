package com.nutrimeal.nutrimeal.repository;

import com.nutrimeal.nutrimeal.model.DeliveryTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryTimeRepository extends JpaRepository<DeliveryTime, Integer> {
}
