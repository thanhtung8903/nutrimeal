package com.nutrimeal.nutrimeal.controller;

import com.nutrimeal.nutrimeal.model.Address;
import com.nutrimeal.nutrimeal.model.Combo;
import com.nutrimeal.nutrimeal.service.ComboService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
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


//    @GetMapping("/consult")
//    public String consult(@RequestParam(value = "calorieValue", required = false) Float calorieValue, Model model) {
//        if (calorieValue != null) {
//            List<Combo> comboList = comboService.getCombosByCalories(calorieValue - 500, calorieValue + 3000);
//            model.addAttribute("comboList", comboList);
//        }
//        return "home/consult";
//    }

    @GetMapping("/consult")
    public String consult(@RequestParam(value = "calorieValue", required = false) Float calorieValue,
                          @RequestParam(value = "calorieResult", required = false) String calorieResult,
                          Model model) {
        if (calorieValue != null) {
            List<Combo> comboList = comboService.getCombosByCalories(calorieValue - 400, calorieValue + 400);
            model.addAttribute("comboList", comboList);
            model.addAttribute("calorieValue", calorieValue);
        }
        if (calorieResult != null) {
            model.addAttribute("calorieResult", calorieResult);
        }
        return "home/consult";
    }


    @GetMapping("/combo")
    public String combo(Model model) {
        List<Combo> comboList = comboService.getAllCombos();
        model.addAttribute("comboList", comboList);
        return "home/combo";
    }
}
