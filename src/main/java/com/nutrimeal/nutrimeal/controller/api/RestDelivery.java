package com.nutrimeal.nutrimeal.controller.api;

import com.nutrimeal.nutrimeal.dto.response.DeliveryDetailResponse;
import com.nutrimeal.nutrimeal.dto.response.DeliveryResponse;
import com.nutrimeal.nutrimeal.model.Delivery;
import com.nutrimeal.nutrimeal.model.User;
import com.nutrimeal.nutrimeal.service.CustomOAuth2User;
import com.nutrimeal.nutrimeal.service.DeliveryService;
import com.nutrimeal.nutrimeal.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
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
        User user = UserAuthentication();
        return deliveryService.findDeliveriesByUser(user);
    }

    @GetMapping("/shipper")
    public List<DeliveryResponse> getDeliveryByShipper() {
        User user = UserAuthentication();
        return deliveryService.findDeliveriesByShipper(user);
    }

    private User UserAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user;

        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oauth2Token = (OAuth2AuthenticationToken) authentication;
            CustomOAuth2User oauthUser = (CustomOAuth2User) oauth2Token.getPrincipal();
            user = userService.findByEmail(oauthUser.getAttribute("email"));
        } else {
            user = userService.findByUsername(authentication.getName());
        }
        return user;
    }

    @PostMapping("/delay/{deliveryId}")
    public ResponseEntity<String> delayDelivery(@PathVariable Integer deliveryId) {
        deliveryService.delayDelivery(deliveryId);
        return ResponseEntity.ok("Delivery delayed");
    }


}
