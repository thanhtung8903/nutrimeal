package com.nutrimeal.nutrimeal.controller;

import com.nutrimeal.nutrimeal.dto.request.LoginRequest;
import com.nutrimeal.nutrimeal.dto.request.SignupRequest;
import com.nutrimeal.nutrimeal.email.EmailService;
import com.nutrimeal.nutrimeal.model.ForgetToken;
import com.nutrimeal.nutrimeal.model.User;
import com.nutrimeal.nutrimeal.service.AuthService;
import com.nutrimeal.nutrimeal.service.ForgetTokenService;
import com.nutrimeal.nutrimeal.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@Data
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;
    private final ForgetTokenService forgetTokenService;
    private final EmailService emailService;

//    logger
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/login")
    public String getLoginForm(Model model, Principal principal) {
        if (principal != null) {
            return "redirect:/";
        }

        if (!model.containsAttribute("LoginRequest")) {
            model.addAttribute("LoginRequest", new LoginRequest());
        }
        return "common/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequest request, Model model) {
        try {
            authService.handleAuthenticateUser(request);
            return "common/home";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "common/login";
        }
    }

    @GetMapping("/signup")
    public String getSignupForm(Model model) {
        if (!model.containsAttribute("SignupRequest")) {
            model.addAttribute("SignupRequest", new SignupRequest());
        }
        return "common/signup";
    }

    @PostMapping("/signup")
    public String signupUser(@ModelAttribute("SignupRequest") SignupRequest request, BindingResult result, Model model) {
        try {
            authService.signupUser(request);
            model.addAttribute("successMessage", "Đăng ký thành công!");
            return "common/signup";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "common/signup";
        }
    }

    @GetMapping("forget")
    public String getForget() {
        return "common/forget";
    }

    @PostMapping("forget")
    public String forget(@RequestParam("usernameOrEmail") String usernameOrEmail, Model model) {
        LOGGER.info("Received forget password request for: {}", usernameOrEmail);
        try {
            User user = userService.getUserByEmailOrUsername(usernameOrEmail, usernameOrEmail);
            if (user != null && user.getUsername() != null) {
                LOGGER.info("User found: {}", user.getUsername());
                forgetTokenService.createOrUpdateForgetToken(user);
                String token = forgetTokenService.getForgetTokenByUser(user);
                LOGGER.info("Generated token for user {}: {}", user.getUsername(), token);
                emailService.forgetPassword(user.getEmail(), user.getUserId(), token, user.getFullName());
                LOGGER.info("Sent forget password email to: {}", user.getEmail());
                model.addAttribute("successMessage", "Vui lòng kiểm tra email để đặt lại mật khẩu!");
            } else {
                LOGGER.warn("User not found or username is null for: {}", usernameOrEmail);
                model.addAttribute("errorMessage", "Không tìm thấy tài khoản");
            }
            model.addAttribute("usernameOrEmail", usernameOrEmail);
            return "common/forget";
        } catch (RuntimeException e) {
            LOGGER.error("Exception occurred while processing forget password request for: {}", usernameOrEmail, e);
            model.addAttribute("usernameOrEmail", usernameOrEmail);
            model.addAttribute("errorMessage", "Lỗi phát sinh, vui lòng thử lại.");
            return "common/forget";
        }
    }

    @GetMapping("forget/{userId}/{token}")
    public String getForgetPassword(@PathVariable("userId") String userId, @PathVariable("token") String token, Model model) {
        try {
            User user = userService.getUserById(userId);
            if (user != null) {
                ForgetToken forgetToken = forgetTokenService.getForgetTokenObjByUser(user);
                if (forgetToken != null && forgetToken.getForgetToken().equals(token)) {
                    LocalDateTime now = LocalDateTime.now();
                    if (now.isAfter(forgetToken.getForgetTokenCreated()) && now.isBefore(forgetToken.getForgetTokenExpired())) {
                        model.addAttribute("userId", userId);
                        model.addAttribute("token", token);
                        return "common/changeForget";
                    } else {
                        return "error/error";
                    }
                } else {
                    return "error/error";
                }
            } else {
                return "error/error";
            }
        } catch (RuntimeException e) {
            return "error/error";
        }
    }

    @PostMapping("forget/change")
    public String changeForgetPassword(@RequestParam("newPassword") String newPassword, @RequestParam("userId") String userId, @RequestParam("token") String token, Model model) {
        try {
            User user = userService.getUserById(userId);
            if (user != null) {
                ForgetToken forgetToken = forgetTokenService.getForgetTokenObjByUser(user);
                if (forgetToken != null && forgetToken.getForgetToken().equals(token)) {
                    LocalDateTime now = LocalDateTime.now();
                    if (now.isAfter(forgetToken.getForgetTokenCreated()) && now.isBefore(forgetToken.getForgetTokenExpired())) {
                        userService.changePasswordForget(newPassword, userId);
                        forgetTokenService.deleteForgetToken(user);
                        model.addAttribute("successMessage", "Đổi mật khẩu thành công!");
                        return "common/forget";
                    } else {
                        return "error/error";
                    }
                } else {
                    return "error/error";
                }
            } else {
                return "error/error";
            }
        } catch (RuntimeException e) {
            return "error/error";
        }
    }
}
