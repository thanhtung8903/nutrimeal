package com.nutrimeal.nutrimeal.controller.api;

import com.nutrimeal.nutrimeal.dto.response.DeliveryResponse;
import com.nutrimeal.nutrimeal.dto.response.ShipperResponse;
import com.nutrimeal.nutrimeal.dto.response.UserInfoResponse;
import com.nutrimeal.nutrimeal.model.User;
import com.nutrimeal.nutrimeal.service.DeliveryService;
import com.nutrimeal.nutrimeal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/shippers")
@RequiredArgsConstructor
public class RestShipper {

    private final UserService userService;
    private final DeliveryService deliveryService;


    @GetMapping()
    public ResponseEntity<List<ShipperResponse>> getAllShippers() {
        List<ShipperResponse> shippers = userService.findAllShipper();
        return ResponseEntity.ok(shippers);
    }

    @GetMapping("/delivery")
    public List<DeliveryResponse> getDeliveryByShipperAndStatus(@RequestParam String status, Principal principal) {
        User shipper = userService.findByUsername(principal.getName());
        return deliveryService.findDeliveriesByShipperAndStatus(shipper, status);
    }

    @PutMapping("/delivery/{deliveryId}")
    public ResponseEntity<DeliveryResponse> updateDeliveryStatus(@PathVariable int deliveryId, @RequestParam String status,
                                                                 @RequestBody(required = false) Map<String, String> note , Principal principal) {
        String noteValue = note.get("note");
        User shipper = userService.findByUsername(principal.getName());
        DeliveryResponse delivery = deliveryService.updateDeliveryStatus(deliveryId, status, shipper, noteValue);
        return ResponseEntity.ok(delivery);
    }


}
