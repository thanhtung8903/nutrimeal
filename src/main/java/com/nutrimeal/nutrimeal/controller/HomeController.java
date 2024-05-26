package com.nutrimeal.nutrimeal.controller;

import com.nutrimeal.nutrimeal.model.Address;
import com.nutrimeal.nutrimeal.model.Combo;
import com.nutrimeal.nutrimeal.model.User;
import com.nutrimeal.nutrimeal.repository.UserRepository;
import com.nutrimeal.nutrimeal.service.ComboService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ComboService comboService;
    private final UserRepository userRepository;

    @GetMapping(value = {"/", "/home"})
    public String home(Model model, Principal principal) {
        boolean isManager = false;
        boolean isAdmin = false;
        if (principal != null) {
            User user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));
            isManager = user.getRoles().stream().anyMatch(role -> role.getRoleName().name().equals("ROLE_MANAGER"));
            isAdmin = user.getRoles().stream().anyMatch(role -> role.getRoleName().name().equals("ROLE_ADMIN"));
        }
        if (isManager) {
            return "manager/managerpage";
        } else if (isAdmin) {
            return "admin/adminpage";
        } else {
            List<Combo> comboList = comboService.getAllCombos();
            model.addAttribute("comboList", comboList);
            return "home";
        }
    }

    @GetMapping("/menu")
    public String menu(Model model) {
        return "home/menu";
    }

    @GetMapping("/faqs")
    public String faqs(Model model) {
        return "home/faqs";
    }

    @GetMapping("/consult")
    public String consult(@RequestParam(value = "calorieValue", required = false) String calorieValue,
                          @RequestParam(value = "calorieResult", required = false) String calorieResult,
                          @RequestParam(value = "age", required = false) String age,
                          @RequestParam(value = "weight", required = false) String weight,
                          @RequestParam(value = "height", required = false) String height,
                          @RequestParam(value = "gender", required = false) String gender,
                          @RequestParam(value = "activityMultiplier", required = false) String activityMultiplier,
                          @RequestParam(value = "weightGoal", required = false) String weightGoal,
                          Model model) {
        if (calorieValue != null) {
            float calorieValueResult = Float.parseFloat(calorieValue);
            if (calorieValueResult > 0) {
                List<Combo> comboList = comboService.getCombosByCalories(calorieValueResult - 400, calorieValueResult + 400);
                model.addAttribute("comboList", comboList);
                model.addAttribute("calorieValue", calorieValue);
                model.addAttribute("comboSuggest", "Combo gợi ý");
            }
        }
        if (calorieResult != null) {
            model.addAttribute("calorieResult", calorieResult);
        }
        if (age != null && weight != null && height != null && gender != null && activityMultiplier != null && weightGoal != null) {
            model.addAttribute("age", age);
            model.addAttribute("weight", weight);
            model.addAttribute("height", height);
            model.addAttribute("gender", gender);
            model.addAttribute("activityMultiplier", activityMultiplier);
            model.addAttribute("weightGoal", weightGoal);
        } else {
            model.addAttribute("age", 25);
            model.addAttribute("weight", 50);
            model.addAttribute("height", 160);
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
