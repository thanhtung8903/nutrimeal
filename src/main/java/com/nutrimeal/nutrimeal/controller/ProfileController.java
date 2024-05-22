package com.nutrimeal.nutrimeal.controller;

import com.nutrimeal.nutrimeal.dto.request.AddressRequest;
import com.nutrimeal.nutrimeal.dto.request.ChangePasswordRequest;
import com.nutrimeal.nutrimeal.dto.request.UpdateUserRequest;
import com.nutrimeal.nutrimeal.model.User;
import com.nutrimeal.nutrimeal.repository.UserRepository;
import com.nutrimeal.nutrimeal.service.UserDetailsImpl;
import com.nutrimeal.nutrimeal.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

@Controller
//@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final HttpServletRequest httpServletRequest;

    @GetMapping("/profile/account")
    public String profileAccount(Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("user", user);
        return "profile/account";
    }

    @PostMapping("/profile/account")
    public String updateProfile(@ModelAttribute UpdateUserRequest updateUserRequest, Principal principal) {
        try {
            userService.updateUser(updateUserRequest, principal.getName());
            return "redirect:/profile/account?success=true";
        } catch (RuntimeException e) {
            return "redirect:/profile/account?error=true";
        }
    }

    @GetMapping("/profile/password")
    public String profilePassword(Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("user", user);
        return "profile/password";
    }

    @PostMapping("/profile/password")
    public String changePassword(@ModelAttribute("ChangePassword") ChangePasswordRequest changePasswordRequest, Model model, Principal principal) {
        try {
            userService.changePassword(changePasswordRequest, principal.getName());
            return "redirect:/profile/password?success=true";
        } catch (RuntimeException e) {
            return "redirect:/profile/password?error=true";
        }
    }

    @GetMapping("/profile/point")
    public String profilePoint(Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("user", user);
        return "profile/point";
    }

    @GetMapping("/profile/address")
    public String profileAddress(Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("user", user);
        return "profile/address";
    }

    @PostMapping("/profile/address")
    public String updateAddress(@ModelAttribute AddressRequest addressRequest, Principal principal) {
        try {
            userService.addNewAddress(addressRequest, principal.getName());
            return "redirect:/profile/address?success=true";
        } catch (RuntimeException e) {
            return "redirect:/profile/address?error=true";
        }
    }

    @GetMapping("/profile/order")
    public String profileOrder(Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("user", user);
        return "profile/order";
    }
}
