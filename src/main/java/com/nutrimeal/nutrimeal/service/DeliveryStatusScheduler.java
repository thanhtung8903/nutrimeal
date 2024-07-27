package com.nutrimeal.nutrimeal.service;

import com.nutrimeal.nutrimeal.model.Delivery;
import com.nutrimeal.nutrimeal.model.DeliveryStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DeliveryStatusScheduler {

    private final DeliveryService deliveryService;

    @Scheduled(cron = "0 0 0 * * ?")
    @Transactional
    public void updateDeliveryStatusAtMidnight() {
        updateStatus();
    }

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void updateDeliveryStatusOnStartup() {
        updateStatus();
    }

    private void updateStatus() {
        List<Delivery> deliveries = deliveryService.findDeliveriesByDeliveryStatusToUpdateStatus(DeliveryStatus.NOT_DELIVERED.name());
        LocalDate today = LocalDate.now();
        for (Delivery delivery : deliveries) {
            LocalDate deliveryDate = ((java.sql.Date) delivery.getDeliveryDate()).toLocalDate();
            if (deliveryDate.isBefore(today)) {
                delivery.setDeliveryStatus(DeliveryStatus.DELIVERY_FAILED.name());
                deliveryService.save(delivery);
            }
        }
    }
}