package com.nutrimeal.nutrimeal.controller.api;

import com.nutrimeal.nutrimeal.dto.response.DeliveryResponse;
import com.nutrimeal.nutrimeal.dto.response.ShipperResponse;
import com.nutrimeal.nutrimeal.dto.response.UserInfoResponse;
import com.nutrimeal.nutrimeal.model.*;
import com.nutrimeal.nutrimeal.service.DeliveryService;
import com.nutrimeal.nutrimeal.service.OrderService;
import com.nutrimeal.nutrimeal.service.PointHistoryService;
import com.nutrimeal.nutrimeal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/shippers")
@RequiredArgsConstructor
public class RestShipper {

    private final UserService userService;
    private final DeliveryService deliveryService;
    private final PointHistoryService pointHistoryService;
    private final OrderService orderService;


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
                                                                 @RequestBody(required = false) Map<String, String> note, Principal principal) {
        String noteValue = note.get("note");
        User shipper = userService.findByUsername(principal.getName());

        Delivery delivery = deliveryService.findDeliveryById(deliveryId);

        if (status.equals("DELIVERED") && Boolean.TRUE.equals(delivery.getIsBonus())) {
            int point = delivery.getOrder().getOrderTotalPrice() / 1000;

            Order order = delivery.getOrder();
            order.setOrderStatus(OrderStatus.COMPLETED);
            orderService.save(order);


            if (point > 0) {
                PointHistory pointHistory = new PointHistory();
                pointHistory.setPointHistoryDescription("Hoàn thành đơn hàng #" + delivery.getOrder().getOrderId());
                pointHistory.setPointHistoryPoint(point);
                pointHistory.setUser(delivery.getOrder().getUser());
                pointHistory.setPointHistoryStatus(PointHistoryStatus.BONUS.name());
                pointHistory.setPointHistoryDate(LocalDate.now());
                delivery.getOrder().getUser().setPoint(delivery.getOrder().getUser().getPoint() + pointHistory.getPointHistoryPoint());
                pointHistoryService.save(pointHistory);
            }
        }

        DeliveryResponse deliveryResponse = deliveryService.updateDeliveryStatus(deliveryId, status, shipper, noteValue);
        return ResponseEntity.ok(deliveryResponse);
    }


}
