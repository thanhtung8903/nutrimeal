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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @GetMapping("/dailymenu/add")
    public String addDailyMenu(Model model) {
        model.addAttribute("listDishV", dishService.findAllByDishType("V"));
        model.addAttribute("listDishN", dishService.findAllByDishType("N"));

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
            dailyMenu.setIsActive(true);
            dailyMenuService.addDailyMenu(dailyMenu);
            return "redirect:/manager/dailymenu/add?success=true";
        } catch (Exception e) {
            return "redirect:/manager/dailymenu/add?error=true";
        }
    }

    @GetMapping("/dailymenu/update/{id}")
    public String updateDailyMenu(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("dailyMenu", dailyMenuService.getDailyMenuById(id));
        model.addAttribute("listDish", dishService.findAllActiveDish());
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

    @GetMapping("/dailymenu/search")
    public String searchDailyMenu(
            @RequestParam(value = "date", required = false) String date,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "dishName", required = false) String dishName,
            @RequestParam(defaultValue = "0") int page,
            Model model
    ) {

        if (date.isEmpty() && type.isEmpty() && dishName.isEmpty()) {
            return "redirect:/manager/dailymenu";
        }

        Date dailyMenuDate = null;
        if (date != null && !date.isEmpty()) {
            try {
                dailyMenuDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Pageable pageable = PageRequest.of(page, 6);
        model.addAttribute("date", date);
        model.addAttribute("type", type);
        model.addAttribute("dishName", dishName != null ? dishName.trim() : null);
        model.addAttribute("listDailyMenu", dailyMenuService.searchDailyMenu(dailyMenuDate, type, dishName != null ? dishName.trim() : null, pageable));
        return "manager/dailymenu/dailyMenu";
    }
}
