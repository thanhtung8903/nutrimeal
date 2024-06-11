package com.nutrimeal.nutrimeal.service;

import com.nutrimeal.nutrimeal.model.Order;
import com.nutrimeal.nutrimeal.model.OrderDetail;
import com.nutrimeal.nutrimeal.repository.OrderDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    public void save(OrderDetail orderDetail) {
        orderDetailRepository.save(orderDetail);
    }

   public List<OrderDetail> getOrdersByOrder(Order order) {
        return orderDetailRepository.findAllByOrder(order);
    }
}
