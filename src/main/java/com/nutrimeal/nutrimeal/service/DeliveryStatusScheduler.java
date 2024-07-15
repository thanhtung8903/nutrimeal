package com.nutrimeal.nutrimeal.service;

import com.nutrimeal.nutrimeal.model.Delivery;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DeliveryStatusScheduler {

    private final DeliveryService deliveryService;

//    @Scheduled(cron = "0 0 0 * * ?")
//    @Transactional
//    public void updateDeliveryStatus() {
//        List<Delivery> deliveries = deliveryService.findDeliveriesByDeliveryStatusToUpdateStatus("NOT_DELIVERED");
//        Date today = new Date();
//        for (Delivery delivery : deliveries) {
//            if (delivery.getDeliveryDate().before(today)) {
//                delivery.setDeliveryStatus("DELIVERY_FAILED");
//                deliveryService.save(delivery);
//            }
//        }
//    }
@Scheduled(cron = "0 0 0 * * ?")
@Transactional
public void updateDeliveryStatus() {
    updateStatus();
}

    // Phương thức bổ sung để kiểm tra trực tiếp
    public void updateStatus() {
        List<Delivery> deliveries = deliveryService.findDeliveriesByDeliveryStatusToUpdateStatus("NOT_DELIVERED");
        Date today = new Date();
        for (Delivery delivery : deliveries) {
            if (delivery.getDeliveryDate().before(today)) {
                delivery.setDeliveryStatus("DELIVERY_FAILED");
                deliveryService.save(delivery);
            }
        }
    }
}
