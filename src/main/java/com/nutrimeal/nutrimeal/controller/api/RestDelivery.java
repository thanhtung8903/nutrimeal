package com.nutrimeal.nutrimeal.controller.api;

import com.nutrimeal.nutrimeal.dto.response.DeliveryDetailResponse;
import com.nutrimeal.nutrimeal.dto.response.DeliveryResponse;
import com.nutrimeal.nutrimeal.model.Delivery;
import com.nutrimeal.nutrimeal.model.User;
import com.nutrimeal.nutrimeal.service.DeliveryService;
import com.nutrimeal.nutrimeal.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/delivery")
@RequiredArgsConstructor
@Slf4j
public class RestDelivery {

    private final DeliveryService deliveryService;

    private final UserService userService;

    @GetMapping()
    public List<DeliveryResponse> getDeliveryByStatus(@RequestParam String status, @RequestParam(required = false) Date date) {
        return deliveryService.findDeliveriesByDeliveryStatus(status);
    }

    @PutMapping("/update/{deliveryId}/{shipperId}")
    public ResponseEntity<String> updateDeliveryStatus(@PathVariable Integer deliveryId, @PathVariable String shipperId) {
        deliveryService.updateDeliveryStatus(deliveryId, shipperId);
        return ResponseEntity.ok("Delivery status updated");
    }

    @GetMapping("/detail/{deliveryId}")
    public ResponseEntity<DeliveryDetailResponse> getDeliveryDetail(@PathVariable Integer deliveryId) {
        return ResponseEntity.ok(deliveryService.findDeliveryDetail(deliveryId));
    }

    @GetMapping("/user")
    public List<DeliveryResponse> getDeliveryByUser() {
        var context = SecurityContextHolder.getContext();

        User user = userService.findByUsername(context.getAuthentication().getName());

        return deliveryService.findDeliveriesByUser(user);
    }

    @GetMapping("/shipper")
    public List<DeliveryResponse> getDeliveryByShipper() {
        var context = SecurityContextHolder.getContext();

        User user = userService.findByUsername(context.getAuthentication().getName());

        return deliveryService.findDeliveriesByShipper(user);
    }

    @PostMapping("/delay/{deliveryId}")
    public ResponseEntity<String> delayDelivery(@PathVariable Integer deliveryId) {
        deliveryService.delayDelivery(deliveryId);
        return ResponseEntity.ok("Delivery delayed");
    }


}
