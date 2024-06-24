package com.nutrimeal.nutrimeal.controller.api;

import com.nutrimeal.nutrimeal.dto.response.OrderResponse;
import com.nutrimeal.nutrimeal.dto.response.UserInfoResponse;
import com.nutrimeal.nutrimeal.model.Order;
import com.nutrimeal.nutrimeal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class RestCustomer {

    private final UserService userService;

    @GetMapping("/{customerId}/orders")
    public ResponseEntity<List<OrderResponse>> getOrdersByCustomerId(@PathVariable String customerId) {
        List<OrderResponse> orders = userService.findOrdersByCustomerId(customerId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping()
    public ResponseEntity<List<UserInfoResponse>> getAllCustomers() {
        List<UserInfoResponse> customers = userService.findAllCustomers();
        return ResponseEntity.ok(customers);
    }
}
