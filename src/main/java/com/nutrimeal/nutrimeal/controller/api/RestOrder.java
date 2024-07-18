package com.nutrimeal.nutrimeal.controller.api;

import com.nutrimeal.nutrimeal.dto.response.OrderDetailResponse;
import com.nutrimeal.nutrimeal.dto.response.OrderResponse;
import com.nutrimeal.nutrimeal.model.Delivery;
import com.nutrimeal.nutrimeal.model.DeliveryStatus;
import com.nutrimeal.nutrimeal.model.Order;
import com.nutrimeal.nutrimeal.model.OrderStatus;
import com.nutrimeal.nutrimeal.service.DeliveryService;
import com.nutrimeal.nutrimeal.service.OrderDetailService;
import com.nutrimeal.nutrimeal.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class RestOrder {

    private final OrderService orderService;
    private final OrderDetailService orderDetailService;
    private final DeliveryService deliveryService;

    @GetMapping()
    public ResponseEntity<List<OrderResponse>> getAllOrders(@RequestParam(required = false) String status) {
        List<OrderResponse> orders = null;
        if (status != null) {
            orders = orderService.findAllOrdersByStatus(status);
        } else {
            orders = orderService.findAllOrders();
        }

        return ResponseEntity.ok(orders);
    }


    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable int orderId) {
        Order order = orderService.getOrderById(orderId);
        OrderResponse orderResponse = orderService.findOrderById(order);
        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping("/{orderId}/orderdetail")
    public ResponseEntity<List<OrderDetailResponse>> getOrderDetailByOrderId(@PathVariable int orderId) {
        Order order = orderService.getOrderById(orderId);
        List<OrderDetailResponse> orders = orderDetailService.findOrderDetailByOrder(order);
        return ResponseEntity.ok(orders);
    }

    @PostMapping("/{orderId}/cancel")
    public ResponseEntity<OrderResponse> cancelOrder(@PathVariable int orderId) {
        Order order = orderService.getOrderById(orderId);
        order.setOrderStatus(OrderStatus.CANCELLED);

        List<Delivery> deliveries = deliveryService.findDeliveriesByOrder(order);

        for (Delivery delivery : deliveries) {
            delivery.setDeliveryStatus(DeliveryStatus.CANCELLED.name());
            deliveryService.save(delivery);
        }

        orderService.save(order);

        OrderResponse orderResponse = orderService.findOrderById(order);
        return ResponseEntity.ok(orderResponse);
    }
}
