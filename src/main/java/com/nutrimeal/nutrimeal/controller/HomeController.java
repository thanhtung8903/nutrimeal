package com.nutrimeal.nutrimeal.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping(value = {"/", "/home"})
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/faqs")
    public String faqs(Model model) {
        return "home/faqs";
    }

    @GetMapping("/address")
    public String address(Model model) {
        return "profile/address2";
    }

}
