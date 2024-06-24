package com.nutrimeal.nutrimeal.controller.api;

import com.nutrimeal.nutrimeal.dto.response.DeliveryResponse;
import com.nutrimeal.nutrimeal.model.Delivery;
import com.nutrimeal.nutrimeal.service.DeliveryService;
import com.nutrimeal.nutrimeal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/delivery")
@RequiredArgsConstructor
public class RestDelivery {

    private final DeliveryService deliveryService;

    private final UserService userService;

    @GetMapping()
    public List<DeliveryResponse> getDeliveryByStatus(@RequestParam String status, @RequestParam(required = false) Date date) {
        return deliveryService.findDeliveriesByDeliveryStatus(status);
    }


}
