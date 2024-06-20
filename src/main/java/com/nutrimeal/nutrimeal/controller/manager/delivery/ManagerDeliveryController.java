package com.nutrimeal.nutrimeal.controller.manager.delivery;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager/delivery")
public class ManagerDeliveryController {

    @GetMapping
    public String managerDelivery() {
        return "manager/delivery/listDelivery";
    }
}
