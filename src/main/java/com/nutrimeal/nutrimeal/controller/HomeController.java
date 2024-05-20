package com.nutrimeal.nutrimeal.controller;

import com.nutrimeal.nutrimeal.service.UserDetailsImpl;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final HttpSession httpSession;

    @GetMapping(value = {"/", "/home"})
    public String home(Model model) {
        if(httpSession.getAttribute("user") != null) {
            UserDetailsImpl user = (UserDetailsImpl) httpSession.getAttribute("user");
            model.addAttribute("username", user.getUsername());
            model.addAttribute("dm", "dmmmm");
        }

        model.addAttribute("vl", "vlllll");
        return "home";
    }

}
