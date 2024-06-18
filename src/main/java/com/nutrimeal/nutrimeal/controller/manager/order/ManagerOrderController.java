package com.nutrimeal.nutrimeal.controller.manager.order;

import com.nutrimeal.nutrimeal.model.Order;
import com.nutrimeal.nutrimeal.service.DeliveryService;
import com.nutrimeal.nutrimeal.service.OrderService;
import com.nutrimeal.nutrimeal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.nutrimeal.nutrimeal.model.OrderStatus.PENDING;
import static com.nutrimeal.nutrimeal.model.OrderStatus.PROCESSING;

@Controller
@RequiredArgsConstructor
@RequestMapping("/manager")
public class ManagerOrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final DeliveryService deliveryService;

    @GetMapping("/order")
    public String dashboard(Model model) {
        List<Order> orders = orderService.getOrdersByStatus(PROCESSING);
        model.addAttribute("ordersProcessing", orders);
        return "manager/order/order";
    }
    @PostMapping("/processingorder")
    public String processingOrder(@RequestParam("orderId") Integer orderId,
                                  @RequestParam("status") String status) {
        orderService.updateStatusOrder(orderId, status);
        deliveryService.createDelivery(orderService.getOrderById(orderId));
        return "redirect:/manager/order";
    }
}
