package com.nutrimeal.nutrimeal.controller.api;

import com.nutrimeal.nutrimeal.dto.response.OrderDetailResponse;
import com.nutrimeal.nutrimeal.dto.response.OrderResponse;
import com.nutrimeal.nutrimeal.model.Order;
import com.nutrimeal.nutrimeal.service.OrderDetailService;
import com.nutrimeal.nutrimeal.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class RestOrder {

    private final OrderService orderService;
    private final OrderDetailService orderDetailService;

    @GetMapping()
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        List<OrderResponse> orders = orderService.findAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{orderId}/orderdetail/")
    public ResponseEntity<List<OrderDetailResponse>> getOrderDetailByOrderId(@PathVariable int orderId) {
        Order order = orderService.getOrderById(orderId);
        List<OrderDetailResponse> orders = orderDetailService.findOrderDetailByOrder(order);
        return ResponseEntity.ok(orders);
    }
}
