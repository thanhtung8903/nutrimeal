package com.nutrimeal.nutrimeal.repository;

import com.nutrimeal.nutrimeal.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findAllByOrderStatus(String orderStatus);
}
