package com.nutrimeal.nutrimeal.controller.shipper;

import com.nutrimeal.nutrimeal.model.User;
import com.nutrimeal.nutrimeal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shipper")
public class ShipperController {

    private final UserService userService;

    @GetMapping("/dashboardShipper")
    public String viewDashboardShipper(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "shipper/dashboardShipper";
    }

    @GetMapping("/orderNotYetDelivery")
    public String viewOrderNotYetDelivery(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "shipper/orderNotYetDelivery";
    }

    @GetMapping("/beingDelivery")
    public String viewBeingDelivery(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "shipper/beingDelivery";
    }

    @GetMapping("/delivered")
    public String viewDelivered(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "shipper/orderDeliverySuccess";
    }

    @GetMapping("/deliveryfailed")
    public String viewDeliveryFailed(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "shipper/orderDeliveryFail";
    }



}
