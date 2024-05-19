package com.nutrimeal.nutrimeal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    @GetMapping(value = {"/", "/home"})
    public String home() {
        return "home";
    }

    @GetMapping("/xinchaodungua/{name}")
    public String dunua(@PathVariable String name, Model model) {
        model.addAttribute("name", name);
        return "Beoo";
    }
}
