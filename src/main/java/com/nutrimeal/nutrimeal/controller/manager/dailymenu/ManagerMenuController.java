package com.nutrimeal.nutrimeal.controller.manager.dailymenu;

import com.nutrimeal.nutrimeal.model.DailyMenu;
import com.nutrimeal.nutrimeal.model.Dish;
import com.nutrimeal.nutrimeal.service.DailyMenuService;
import com.nutrimeal.nutrimeal.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerMenuController {

    private final DishService dishService;
    private final DailyMenuService dailyMenuService;


    @GetMapping("/dailymenu")
    public String dish(Model model, @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 6);
        model.addAttribute("listDailyMenu", dailyMenuService.getAllDailyMenu(pageable));
        return "manager/dailymenu/dailyMenu";
    }

    @GetMapping("/api/dishes")
    @ResponseBody
    public ResponseEntity<List<Dish>> getDishes(@RequestParam String type) {
        List<Dish> dishes = dishService.findAllByDishType(type);
        return ResponseEntity.ok(dishes);
    }

    @GetMapping("/dailymenu/add")
    public String addDailyMenu(Model model, @RequestParam(value = "dailyMenuType", required = false) String type) {
        if (type == null) {
            model.addAttribute("listDish", dishService.findAllDish());
        }else{
            model.addAttribute("listDish", dishService.findAllByDishType(type));
        }
        return "manager/dailymenu/addDailyMenu";
    }

    @PostMapping("/dailymenu/add")
    public String addDailyMenu(@ModelAttribute DailyMenu dailyMenu,
                               @RequestParam(value = "menuBreakfast", required = true) String menuBreakfast,
                               @RequestParam(value = "menuLunch", required = true) String menuLunch,
                               @RequestParam(value = "menuDinner", required = true) String menuDinner) {

        try {
            if (dailyMenuService.existsDailyMenuByDailyMenuDateAndDailyMenuType(dailyMenu.getDailyMenuDate(), dailyMenu.getDailyMenuType())) {
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

    @GetMapping("/dailymenu/update/{id}")
    public String updateDailyMenu(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("dailyMenu", dailyMenuService.getDailyMenuById(id));
        model.addAttribute("listDish", dishService.findAllDish());
        return "manager/dailymenu/updateDailyMenu";
    }

    @PostMapping("/dailymenu/update/{id}")
    public String updateDailyMenu(@PathVariable("id") Integer id, @ModelAttribute DailyMenu dailyMenu,
                                  @RequestParam(value = "menuBreakfast", required = true) String menuBreakfast,
                                  @RequestParam(value = "menuLunch", required = true) String menuLunch,
                                  @RequestParam(value = "menuDinner", required = true) String menuDinner) {
        try {
            DailyMenu existingDailyMenu = dailyMenuService.getDailyMenuById(id);
            if (existingDailyMenu == null) {
                return "redirect:/manager/dailymenu/update/" + id + "?error=true";
            }

            boolean isDateChanged = !existingDailyMenu.getDailyMenuDate().equals(dailyMenu.getDailyMenuDate());
            boolean isTypeChanged = !existingDailyMenu.getDailyMenuType().equals(dailyMenu.getDailyMenuType());

            if (isDateChanged && dailyMenuService.existsDailyMenuByDailyMenuDateAndDailyMenuType(dailyMenu.getDailyMenuDate(), dailyMenu.getDailyMenuType())) {
                return "redirect:/manager/dailymenu/update/" + id + "?errorduplicate=true";
            } else if (!isDateChanged && isTypeChanged && dailyMenuService.existsDailyMenuByDailyMenuDateAndDailyMenuType(dailyMenu.getDailyMenuDate(), dailyMenu.getDailyMenuType())) {
                return "redirect:/manager/dailymenu/update/" + id + "?errorduplicate=true";
            }
            Dish dishBreakfast = dishService.getDishById(Integer.parseInt(menuBreakfast));
            Dish dishLunch = dishService.getDishById(Integer.parseInt(menuLunch));
            Dish dishDinner = dishService.getDishById(Integer.parseInt(menuDinner));

            dailyMenu.setDishBreakfast(dishBreakfast);
            dailyMenu.setDishLunch(dishLunch);
            dailyMenu.setDishDinner(dishDinner);

            dailyMenuService.updateDailyMenu(id, dailyMenu);
            return "redirect:/manager/dailymenu/update/" + id + "?success=true";
        } catch (Exception e) {
            return "redirect:/manager/dailymenu/update/" + id + "?error=true";
        }
    }

    @GetMapping("/dailymenu/delete/{id}")
    public String deleteCombo(@PathVariable int id) {
        dailyMenuService.deleteDailyMenu(id);
        return "redirect:/manager/dailymenu";
    }
}
