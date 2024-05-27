package com.nutrimeal.nutrimeal.controller.manager.dish;

import com.nutrimeal.nutrimeal.model.Dish;
import com.nutrimeal.nutrimeal.service.DishService;
import com.nutrimeal.nutrimeal.service.ImageUploadService;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@RequestMapping("/manager")
public class ManagerDishController {

    private final DishService dishService;
    private final ImageUploadService imageUploadService;

//    @GetMapping("/dish")
//    public String dish(Model model) {
//        model.addAttribute("listDish", dishService.findAllDish());
//        return "manager/dish/dish";
//    }

    @GetMapping("/dish")
    public String dish(Model model, @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 6);
        model.addAttribute("listDish", dishService.findAllDish(pageable));
        return "manager/dish/dish";
    }

    @GetMapping("/dish/add")
    public String addDish() {
        return "manager/dish/addDish";
    }

    @PostMapping("/dish/add")
    public String addDish(@ModelAttribute Dish dish, @RequestParam(value = "image", required = false) MultipartFile image) {
        try {
            if (image != null && !image.isEmpty()) {
                dish.setDishImage(imageUploadService.uploadFile(image));
            } else {
                dish.setDishImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRNk7lxboBaTCE78SGmXH8pM4Gx3uXLf8m_gZUtpEPdTA&s"); // or set to a default image URL
            }
            dishService.save(dish);
            return "redirect:/manager/dish/add?success=true";
        } catch (Exception e) {
            return "redirect:/manager/dish/add?error=true";
        }
    }

    @GetMapping("/dish/update/{id}")
    public String updateDish(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("dish", dishService.findDishById(id));
        return "manager/dish/updateDish";
    }

    @PostMapping("/dish/update/{id}")
    public String updateDish(@PathVariable("id") Integer id, @ModelAttribute Dish dish, @RequestParam(value = "image", required = false) MultipartFile image) {
        try {
            Dish oldDish = dishService.findDishById(id);
            if (image != null && !image.isEmpty()) {
                dish.setDishImage(imageUploadService.uploadFile(image));
            } else {
                dish.setDishImage(oldDish.getDishImage());
            }
            dishService.updateDish(id, dish);
            return "redirect:/manager/dish/update/" + id + "?success=true";
        } catch (Exception e) {
            return "redirect:/manager/dish/update/" + id + "?error=true";
        }
    }

    @GetMapping("/dish/delete/{id}")
    public String deleteDish(@PathVariable("id") Integer id) {
        dishService.deleteDish(id);
        return "redirect:/manager/dish";
    }

}
