package com.nutrimeal.nutrimeal.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.nutrimeal.nutrimeal.model.Delivery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Arrays;
import java.util.List;

public class DeliveryStatusSchedulerTest {

    @Mock
    private DeliveryService deliveryService;

    @InjectMocks
    private DeliveryStatusScheduler deliveryStatusScheduler;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUpdateStatus() {
        // Tạo một danh sách các đơn hàng giả
        Delivery delivery1 = new Delivery();
        delivery1.setDeliveryDate(new Date(System.currentTimeMillis() - 86400000)); // Hôm qua
        delivery1.setDeliveryStatus("NOT_DELIVERED");

        Delivery delivery2 = new Delivery();
        delivery2.setDeliveryDate(new Date(System.currentTimeMillis() + 86400000)); // Ngày mai
        delivery2.setDeliveryStatus("NOT_DELIVERED");

        List<Delivery> deliveries = Arrays.asList(delivery1, delivery2);

        // Giả lập phương thức findDeliveriesByDeliveryStatusToUpdateStatus
        when(deliveryService.findDeliveriesByDeliveryStatusToUpdateStatus("NOT_DELIVERED")).thenReturn(deliveries);

        // Gọi phương thức updateStatus
        deliveryStatusScheduler.updateStatus();

        // Kiểm tra kết quả
        assertEquals("DELIVERY_FAILED", delivery1.getDeliveryStatus());
        assertEquals("NOT_DELIVERED", delivery2.getDeliveryStatus());

        // Kiểm tra phương thức save được gọi đúng
        verify(deliveryService).save(delivery1);
        verify(deliveryService, never()).save(delivery2);
    }
}
