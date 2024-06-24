package com.nutrimeal.nutrimeal.controller.api;

import com.nutrimeal.nutrimeal.model.DailyMenu;
import com.nutrimeal.nutrimeal.service.DailyMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dailyMenu")
@RequiredArgsConstructor
public class RestDailyMenu {

    private final DailyMenuService dailyMenuService;

    @GetMapping("/{id}")
    public DailyMenu getDailyMenuById(@PathVariable Integer id) {
        return dailyMenuService.getDailyMenuById(id);
    }
}
