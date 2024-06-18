package com.nutrimeal.nutrimeal.controller.api;

import com.nutrimeal.nutrimeal.model.Dish;
import com.nutrimeal.nutrimeal.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dishes")
@RequiredArgsConstructor
public class RestDish {

    private final DishService dishService;

    @GetMapping("/{type}")
    public List<Dish> getDishesByType(@PathVariable String type) {
        return dishService.findAllByDishType(type);
    }

}
