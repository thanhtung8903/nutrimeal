package com.nutrimeal.nutrimeal.controller;

import com.nutrimeal.nutrimeal.model.Combo;
import com.nutrimeal.nutrimeal.service.ComboService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ComboService comboService;

    @GetMapping(value = {"/", "/home"})
    public String home(Model model) {
        List<Combo> comboList = comboService.getAllCombos();
        model.addAttribute("comboList", comboList);
        return "home";
    }

    @GetMapping("/faqs")
    public String faqs(Model model) {
        return "home/faqs";
    }

    @GetMapping("/consult")
    public String consult(Model model) {
        return "home/consult";
    }

    @GetMapping("/combo")
    public String combo(Model model) {
        List<Combo> comboList = comboService.getAllCombos();
        model.addAttribute("comboList", comboList);
        return "home/combo";
    }
}
