package com.nutrimeal.nutrimeal.controller;

import com.nutrimeal.nutrimeal.dto.request.ChangePasswordRequest;
import com.nutrimeal.nutrimeal.dto.request.UpdateUserRequest;
import com.nutrimeal.nutrimeal.model.User;
import com.nutrimeal.nutrimeal.repository.UserRepository;
import com.nutrimeal.nutrimeal.service.ImageUploadService;
import com.nutrimeal.nutrimeal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
//@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final ImageUploadService imageUploadService;

    @GetMapping("/profile/account")
    public String profileAccount(Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("user", user);
        return "profile/account";
    }

    @PostMapping("/profile/account")
    public String updateProfile(
            @ModelAttribute UpdateUserRequest updateUserRequest,
            @RequestParam("imageuser") MultipartFile multipartFile,
            Principal principal) {
        try {
            User user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));
            if (multipartFile != null && !multipartFile.isEmpty() && multipartFile.getBytes().length > 0) {
                String imageURL = imageUploadService.uploadFile(multipartFile);
                updateUserRequest.setImage(imageURL);
            } else {
                updateUserRequest.setImage(user.getImage()); // keep the old image if no new file is uploaded
            }
            userService.updateUser(updateUserRequest, principal.getName());
            return "redirect:/profile/account?success=true";
        } catch (RuntimeException e) {
            return "redirect:/profile/account?error=true";
        } catch (IOException e) {
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

    @GetMapping("/profile/order")
    public String profileOrder(Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("user", user);
        return "profile/order";
    }
}