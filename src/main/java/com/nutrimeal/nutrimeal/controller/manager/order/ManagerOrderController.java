package com.nutrimeal.nutrimeal.controller.manager.order;

import com.nutrimeal.nutrimeal.model.Order;
import com.nutrimeal.nutrimeal.model.PointHistory;
import com.nutrimeal.nutrimeal.model.PointHistoryStatus;
import com.nutrimeal.nutrimeal.service.DeliveryService;
import com.nutrimeal.nutrimeal.service.OrderService;
import com.nutrimeal.nutrimeal.service.PointHistoryService;
import com.nutrimeal.nutrimeal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
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
    private final PointHistoryService pointHistoryService;


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
        Order order = orderService.getOrderById(orderId);
        Integer points = order.getPoint(); // Get points as Integer to handle null
        if (status.equals("CANCELLED")) {
            PointHistory pointHistory = new PointHistory();
            pointHistory.setPointHistoryDescription("Hoàn điểm đơn hàng #" + orderId);
            // Check if points is not null before calling intValue()
            pointHistory.setPointHistoryPoint(points != null ? points.intValue() : 0);
            pointHistory.setUser(order.getUser());
            pointHistory.setPointHistoryStatus(PointHistoryStatus.BONUS.name());
            pointHistory.setPointHistoryDate(LocalDate.now());
            order.getUser().setPoint(order.getUser().getPoint() + pointHistory.getPointHistoryPoint());
            pointHistoryService.save(pointHistory);
        }
        if (status.equals("SHIPPED")) {
            deliveryService.createDelivery(order);
        }
        return "redirect:/manager/order";
    }
}
