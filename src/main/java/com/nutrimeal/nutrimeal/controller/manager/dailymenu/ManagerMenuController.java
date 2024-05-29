package com.nutrimeal.nutrimeal.controller.manager.dailymenu;

import com.nutrimeal.nutrimeal.model.DailyMenu;
import com.nutrimeal.nutrimeal.model.Dish;
import com.nutrimeal.nutrimeal.service.DailyMenuService;
import com.nutrimeal.nutrimeal.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerMenuController {

    private final DishService dishService;
    private final DailyMenuService dailyMenuService;

//    @GetMapping("/dailymenu")
//    public String getDailyMenu(Model model) {
//        model.addAttribute("listDailyMenu", dailyMenuService.getAllDailyMenu());
//        return "manager/dailymenu/dailyMenu";
//    }

    @GetMapping("/dailymenu")
    public String dish(Model model, @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 6);
        model.addAttribute("listDailyMenu", dailyMenuService.getAllDailyMenu(pageable));
        return "manager/dailymenu/dailyMenu";
    }

    @GetMapping("/dailymenu/add")
    public String addDailyMenu(Model model) {
        model.addAttribute("listDish", dishService.findAllDish());
        return "manager/dailymenu/addDailyMenu";
    }

    @PostMapping("/dailymenu/add")
    public String addDailyMenu(@ModelAttribute DailyMenu dailyMenu,
                               @RequestParam(value = "menuBreakfast", required = true) String menuBreakfast,
                               @RequestParam(value = "menuLunch", required = true) String menuLunch,
                               @RequestParam(value = "menuDinner", required = true) String menuDinner) {

        try {
            if(dailyMenuService.existsDailyMenuByDailyMenuDateAndDailyMenuType(dailyMenu.getDailyMenuDate(), dailyMenu.getDailyMenuType())) {
                return "redirect:/manager/dailymenu/add?errorduplicate=true";
            }
            Dish dishBreakfast = dishService.getDishById(Integer.parseInt(menuBreakfast));
            Dish dishLunch = dishService.getDishById(Integer.parseInt(menuLunch));
            Dish dishDinner = dishService.getDishById(Integer.parseInt(menuDinner));
            dailyMenu.setDishBreakfast(dishBreakfast);
            dailyMenu.setDishLunch(dishLunch);
            dailyMenu.setDishDinner(dishDinner);
            dailyMenuService.addDailyMenu(dailyMenu);
            return "redirect:/manager/dailymenu/add?success=true";
        } catch (Exception e) {
            return "redirect:/manager/dailymenu/add?error=true";
        }
    }

}
