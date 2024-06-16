package com.nutrimeal.nutrimeal.service;

import com.nutrimeal.nutrimeal.model.Order;
import com.nutrimeal.nutrimeal.model.User;
import com.nutrimeal.nutrimeal.repository.OrderBasketRepository;
import com.nutrimeal.nutrimeal.repository.OrderDetailRepository;
import com.nutrimeal.nutrimeal.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderBasketRepository orderBasketRepository;
    private final OrderDetailRepository orderDetailRepository;

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByStatus(String status) {
        return orderRepository.findAllByOrderStatus(status);
    }

    public Order getOrderById(Integer id) {
        return orderRepository.findById(id).orElse(null);
    }

    public void updateStatusOrder(Integer id, String status) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            order.setOrderStatus(status);
            orderRepository.save(order);
        }
    }

    public void createOrder(User user, Integer price) {
        Order order = new Order();
        order.setUser(user);
        order.setOrderTotalPrice(price);
        orderRepository.save(order);
    }

    public void save(Order order){
        orderRepository.save(order);
    }

    public List<Order> getOrdersByUserAndStatus(User user, String status) {
        return orderRepository.findAllByUserAndOrderStatus(user, status);
    }

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }
}
