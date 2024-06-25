package com.nutrimeal.nutrimeal.controller.api;

import com.nutrimeal.nutrimeal.dto.response.DeliveryResponse;
import com.nutrimeal.nutrimeal.model.Delivery;
import com.nutrimeal.nutrimeal.service.DeliveryService;
import com.nutrimeal.nutrimeal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static com.nutrimeal.nutrimeal.model.DeliveryStatus.DELIVERED;
import static com.nutrimeal.nutrimeal.model.DeliveryStatus.IN_TRANSIT;

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

    @PostMapping("/processingdelivery")
    public ResponseEntity<String> processingDelivery(@RequestParam Integer deliveryId,
                                                     @RequestParam String status, @RequestParam String note) {
        deliveryService.updateDelivery(deliveryId, status, note);
        if (status.equals(IN_TRANSIT.toString())) {
            return ResponseEntity.ok("Bắt đầu giao hàng");
        } else if (status.equals(DELIVERED.toString())) {
            return ResponseEntity.ok("Giao hàng thành công");
        } else {
            return ResponseEntity.ok("Giao hàng thất bại");
        }
    }

}
