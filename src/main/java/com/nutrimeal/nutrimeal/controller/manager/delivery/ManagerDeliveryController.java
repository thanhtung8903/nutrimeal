package com.nutrimeal.nutrimeal.controller.manager.delivery;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager/delivery")
public class ManagerDeliveryController {

    @GetMapping("/notdelivered")
    public String managerDeliveryNotDelivered() {
        return "manager/delivery/notDelivered";
    }

    @GetMapping("/intransit")
    public String managerDeliveryIntransit() {
        return "manager/delivery/inTransit";
    }

    @GetMapping("/delivered")
    public String managerDeliveryDelivered() {
        return "manager/delivery/delivered";
    }

    @GetMapping("/deliveryfailed")
    public String managerDeliveryFailed() {
        return "manager/delivery/deliveredFailed";
    }

    @GetMapping("/delayed")
    public String managerDeliveryDelayed() {
        return "manager/delivery/delayedDelivery";
    }
}
