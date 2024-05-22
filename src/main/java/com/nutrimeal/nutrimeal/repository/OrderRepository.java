package com.nutrimeal.nutrimeal.repository;

import com.nutrimeal.nutrimeal.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
