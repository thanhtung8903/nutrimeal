package com.nutrimeal.nutrimeal.controller.order;

import com.nutrimeal.nutrimeal.dto.request.OrderRequest;
import com.nutrimeal.nutrimeal.model.Order;
import com.nutrimeal.nutrimeal.model.User;
import com.nutrimeal.nutrimeal.repository.UserRepository;
import com.nutrimeal.nutrimeal.service.OrderService;
import com.nutrimeal.nutrimeal.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final VNPayService vnPayService;
    private final UserService userService;

    @GetMapping("/order/confirm/{id}")
    public String confirmOrder(Model model, @PathVariable Integer id) {
        model.addAttribute("order", orderService.getOrderById(id));
        model.addAttribute("orderDetailsList", orderService.getOrderById(id));
        return "order/confirmOrder";
    }

    @GetMapping("/order/confirm/vnpay-payment")
    public String GetMapping(HttpServletRequest request, Principal principal, Model model){
        if (principal == null) {
            return "redirect:/login";
        }
        User user;
        if (principal instanceof OAuth2AuthenticationToken token) {
            OAuth2User oauthUser = token.getPrincipal();
            user = userService.findByEmail(oauthUser.getAttribute("email"));
        } else {
            user = userService.findByUsername(principal.getName());
        }
        int orderId =vnPayService.orderReturn(request, user);
        model.addAttribute("order", orderService.getOrderById(orderId));
        model.addAttribute("orderDetailsList", orderService.getOrderById(orderId));
        return "order/confirmOrder";
    }

}
