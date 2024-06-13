package com.nutrimeal.nutrimeal.controller.manager.customer;

import com.nutrimeal.nutrimeal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ManagerCustomerController {

    private final UserService userService;

    @GetMapping("/manager/customer")
    public String customer(Model model) {
        model.addAttribute("listCustomer", userService.findAllCustomer());
        return "manager/customer/customer";
    }
}
