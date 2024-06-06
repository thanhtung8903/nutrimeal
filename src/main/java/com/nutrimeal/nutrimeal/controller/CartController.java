package com.nutrimeal.nutrimeal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {

    @GetMapping("/cart")
    public String getCart() {
        return "order/cart";
    }
}
