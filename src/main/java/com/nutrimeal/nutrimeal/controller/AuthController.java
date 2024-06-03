package com.nutrimeal.nutrimeal.controller;

import com.nutrimeal.nutrimeal.dto.request.LoginRequest;
import com.nutrimeal.nutrimeal.dto.request.SignupRequest;
import com.nutrimeal.nutrimeal.email.EmailService;
import com.nutrimeal.nutrimeal.model.ForgetToken;
import com.nutrimeal.nutrimeal.model.User;
import com.nutrimeal.nutrimeal.repository.UserRepository;
import com.nutrimeal.nutrimeal.service.AuthService;
import com.nutrimeal.nutrimeal.service.ForgetTokenService;
import com.nutrimeal.nutrimeal.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.boot.Banner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    public String getLoginForm(Model model) {
        if (!model.containsAttribute("LoginRequest")) {
            model.addAttribute("LoginRequest", new LoginRequest());
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequest request, Model model) {
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
            model.addAttribute("successMessage", "Đăng ký thành công!");
            return "signup";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "signup";
        }
    }

    @GetMapping("forget")
    public String getForget() {
        return "forget";
    }

    @PostMapping("forget")
    public String forget(@RequestParam("usernameOrEmail") String usernameOrEmail, Model model) {
        try {
            User user = userService.getUserByEmailOrUsername(usernameOrEmail, usernameOrEmail);
            if (user != null && user.getUsername() != null) {
                forgetTokenService.createOrUpdateForgetToken(user);
                String token = forgetTokenService.getForgetTokenByUser(user);
                emailService.forgetPassword(user.getEmail(), user.getUserId(), token);
                model.addAttribute("successMessage", "Vui lòng kiểm tra email để đặt lại mật khẩu!");
            } else {
                model.addAttribute("errorMessage", "Không tìm thấy tài khoản!");
            }
            model.addAttribute("usernameOrEmail", usernameOrEmail);
            return "forget";
        } catch (RuntimeException e) {
            model.addAttribute("usernameOrEmail", usernameOrEmail);
            model.addAttribute("errorMessage", "Không tìm thấy tài khoản!");
            return "forget";
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
                        return "changeForget";
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
                        return "forget";
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
