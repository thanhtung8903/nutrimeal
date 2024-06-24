package com.nutrimeal.nutrimeal.service;

import com.nutrimeal.nutrimeal.dto.response.OrderDetailResponse;
import com.nutrimeal.nutrimeal.model.Order;
import com.nutrimeal.nutrimeal.model.OrderDetail;
import com.nutrimeal.nutrimeal.repository.OrderDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    public void save(OrderDetail orderDetail) {
        orderDetailRepository.save(orderDetail);
    }

   public List<OrderDetail> getOrderDetailsByOrder(Order order) {
        return orderDetailRepository.findAllByOrder(order);
    }

    public List<OrderDetailResponse> findOrderDetailByOrder(Order order) {
        List<OrderDetail> orderDetails = orderDetailRepository.findAllByOrder(order);
        return orderDetails.stream()
                .map(orderDetail -> OrderDetailResponse.builder()
                        .id(orderDetail.getOrderDetailId())
                        .quantity(orderDetail.getOrderDetailQuantity())
                        .price(orderDetail.getOrderDetailPrice())
                        .comboName(orderDetail.getCombo().getComboName())
                        .comboImage(orderDetail.getCombo().getComboImage())
                        .comboDay(orderDetail.getComboDay() == 7 ? "Gói tuần" : "Gói tháng").build())
                .collect(Collectors.toList());
    }
}
