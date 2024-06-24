package com.nutrimeal.nutrimeal.controller.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/manager")
@Controller
@RequiredArgsConstructor
public class Managerdashboard {

    @GetMapping("/")
    public String managerDashboard() {
        return "manager/managerpage";
    }
}
