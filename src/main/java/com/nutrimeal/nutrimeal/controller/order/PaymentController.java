package com.nutrimeal.nutrimeal.controller.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PaymentController {

    @GetMapping("/payment")
    public String showPayment() {
        return "order/payment";
    }
}
