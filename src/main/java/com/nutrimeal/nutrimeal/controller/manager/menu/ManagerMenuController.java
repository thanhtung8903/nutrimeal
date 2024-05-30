package com.nutrimeal.nutrimeal.controller.manager.menu;

import com.nutrimeal.nutrimeal.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerMenuController {

    private final DishService dishService;


}
