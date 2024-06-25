package com.nutrimeal.nutrimeal.controller.shipper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class Demo {
    @GetMapping("/beingDelivery")
    public String beingDelivery() {
        return "Shipper/sidebarShipper";
    }
}
