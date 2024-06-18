package com.nutrimeal.nutrimeal.repository;

import com.nutrimeal.nutrimeal.model.DeliveryDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryDetailRepository extends JpaRepository<DeliveryDetail, Integer> {
}
