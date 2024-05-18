package com.nutrimeal.nutrimeal.controller;

import com.nutrimeal.nutrimeal.dto.request.SignupRequest;
import com.nutrimeal.nutrimeal.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/signup")
    public String getSignup(){
        return "signup";
    }

    @PostMapping("/signup")
    public String postSignup(@ModelAttribute SignupRequest signupRequest){
        authService.handleRegisterUser(signupRequest);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(){
        return "redirect:/";
    }

    @GetMapping("/")
    public String getHome(){
        return "home";
    }

}