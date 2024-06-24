package com.nutrimeal.nutrimeal.controller;

import com.nutrimeal.nutrimeal.model.Combo;
import com.nutrimeal.nutrimeal.model.DailyMenu;
import com.nutrimeal.nutrimeal.model.Dish;
import com.nutrimeal.nutrimeal.model.User;
import com.nutrimeal.nutrimeal.repository.UserRepository;
import com.nutrimeal.nutrimeal.service.ComboService;
import com.nutrimeal.nutrimeal.service.DailyMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ComboService comboService;
    private final UserRepository userRepository;
    private final DailyMenuService dailyMenuService;

    @GetMapping(value = {"/", "/home"})
    public String home(Model model, Principal principal) {
        boolean isManager = false;
        boolean isAdmin = false;

        if (principal instanceof OAuth2AuthenticationToken && principal != null) {
            OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) principal;
            OAuth2User oauthUser = token.getPrincipal();
            User user = userRepository.findByEmail(oauthUser.getAttribute("email")).orElse(null);
            isManager = user.getRoles().stream().anyMatch(role -> role.getRoleName().name().equals("ROLE_MANAGER"));
            isAdmin = user.getRoles().stream().anyMatch(role -> role.getRoleName().name().equals("ROLE_ADMIN"));
            boolean isOauth2User = principal instanceof OAuth2AuthenticationToken && principal != null;
            model.addAttribute("isOauth2User", isOauth2User);
            if (isManager) {
                return "redirect:/manager/customer";
            } else if (isAdmin) {
                return "redirect:/manager/customer";
            } else {
                List<Combo> comboList = comboService.getAllComboActive();
                model.addAttribute("comboList", comboList);
                return "common/home";
            }
        } else {
            if (principal != null) {
                User user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));
                isManager = user.getRoles().stream().anyMatch(role -> role.getRoleName().name().equals("ROLE_MANAGER"));
                isAdmin = user.getRoles().stream().anyMatch(role -> role.getRoleName().name().equals("ROLE_ADMIN"));
            }
            if (isManager) {
                return "redirect:/manager/customer";
            } else if (isAdmin) {
                return "redirect:/manager/customer";
            } else {
                List<Combo> comboList = comboService.getAllComboActive();
                model.addAttribute("comboList", comboList);
                return "common/home";
            }
        }
    }

    @GetMapping("/menu")
    public String menu(Model model) {
        // Lấy ngày hiện tại
        Date today = new Date();

        // Tạo đối tượng Calendar để tính toán ngày sau 7 ngày
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_MONTH, 6);
        Date todayPlus6 = calendar.getTime();

        // Định dạng ngày tháng theo kiểu YYYY-MM-DD
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM");
        String SToday = formatter.format(today);
        String future = formatter.format(todayPlus6);

        model.addAttribute("today", SToday);
        model.addAttribute("future", future);

        // Gọi phương thức service để lấy danh sách DailyMenu
        List<DailyMenu> listDailyMenu = dailyMenuService.findByDailyMenuDateBetween(today, todayPlus6);
        model.addAttribute("listDailyMenu", listDailyMenu);

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
            int calorieValueResult = Integer.parseInt(calorieValue);
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
        List<Combo> comboList = comboService.getAllComboActive();
        model.addAttribute("comboList", comboList);
        return "home/combo";
    }

    @GetMapping("/combo/{id}")
    public String comboDetail(@PathVariable("id") Integer id, Model model) {
        Combo combo = comboService.getComboById(id);
        model.addAttribute("combo", combo);
        String[] descriptionItems = combo.getComboDescription().split("\n");
        model.addAttribute("descriptionItems", descriptionItems);
        model.addAttribute("comboList", comboService.getAllComboActive());


        Date today = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        Date todayPlus7 = calendar.getTime();
        // Gọi phương thức service để lấy danh sách DailyMenu
        List<DailyMenu> listDailyMenu = dailyMenuService.findByDailyMenuDateBetween(today, todayPlus7);
        model.addAttribute("listDailyMenu", listDailyMenu);

        List<Dish> dishList = new ArrayList<>();

        if (combo.getComboType().getComboTypeId() == 1) {
            for (DailyMenu dailyMenu : listDailyMenu) {
                if (dailyMenu.getDailyMenuType().equals("V")) {
                    dishList.add(dailyMenu.getDishBreakfast());
                    dishList.add(dailyMenu.getDishLunch());
                    dishList.add(dailyMenu.getDishDinner());

                }
            }
        } else {
            for (DailyMenu dailyMenu : listDailyMenu) {
                if (dailyMenu.getDailyMenuType().equals("N")) {
                    dishList.add(dailyMenu.getDishBreakfast());
                    dishList.add(dailyMenu.getDishLunch());
                    dishList.add(dailyMenu.getDishDinner());
                }
            }
        }

        model.addAttribute("dishList", dishList);


        return "home/comboDetail";
    }


}
