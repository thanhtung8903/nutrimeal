package com.nutrimeal.nutrimeal.controller;

import com.nutrimeal.nutrimeal.dto.request.LoginRequest;
import com.nutrimeal.nutrimeal.dto.request.SignupRequest;
import com.nutrimeal.nutrimeal.service.AuthService;
import com.nutrimeal.nutrimeal.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Data
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/login")
    public String getLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("LoginRequest") LoginRequest request, BindingResult result, Model model) {
        try {
            authService.handleAuthenticateUser(request);
            return "home";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "login";
        }
    }

    @GetMapping("/signup")
    public String getSignupForm(Model model) {
        if (!model.containsAttribute("SignupRequest")) {
            model.addAttribute("SignupRequest", new SignupRequest());
        }
        return "signup";
    }

    @PostMapping("/signup")
    public String signupUser(@ModelAttribute("SignupRequest") SignupRequest request, BindingResult result, Model model) {
        try {
            authService.signupUser(request);
            model.addAttribute("successMessage", "User registered successfully!");
            return "signup";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "signup";
        }
    }



}
