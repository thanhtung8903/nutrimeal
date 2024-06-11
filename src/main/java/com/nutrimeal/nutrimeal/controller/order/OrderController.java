package com.nutrimeal.nutrimeal.controller.order;

import com.nutrimeal.nutrimeal.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/order/confirm/{id}")
    public String confirmOrder(Model model, @PathVariable Integer id) {
        model.addAttribute("order", orderService.getOrderById(id));
        model.addAttribute("orderDetailsList", orderService.getOrderById(id));
        return "order/confirmOrder";
    }
}
