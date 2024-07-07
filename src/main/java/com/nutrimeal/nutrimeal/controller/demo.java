package com.nutrimeal.nutrimeal.controller;

import com.nutrimeal.nutrimeal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class demo {
    @GetMapping("/dashboardShipper")
    public String dashboardShipper() {
        return "/dashboardShipper";
    }
}
