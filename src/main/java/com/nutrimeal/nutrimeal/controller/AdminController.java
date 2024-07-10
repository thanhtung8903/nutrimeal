package com.nutrimeal.nutrimeal.controller;

import com.nutrimeal.nutrimeal.model.User;
import com.nutrimeal.nutrimeal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    @GetMapping("/dashboard")
    public String customer(Model model,  Principal principal) {
        model.addAttribute("listUser", userService.getAllUsers());
        return "dashboardAdmin";
    }
}
