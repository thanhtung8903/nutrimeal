package com.nutrimeal.nutrimeal.controller.order;

import com.nutrimeal.nutrimeal.dto.request.CallRequest;
import com.nutrimeal.nutrimeal.model.Order;
import com.nutrimeal.nutrimeal.model.OrderStatus;
import com.nutrimeal.nutrimeal.model.User;
import com.nutrimeal.nutrimeal.service.OrderService;
import com.nutrimeal.nutrimeal.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.List;
import java.util.Random;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final VNPayService vnPayService;
    private final UserService userService;
    private final RestTemplate restTemplate;

    private int countMakeCall = 0;

    @GetMapping("/order/confirm/{id}")
    public String confirmOrder(Model model, @PathVariable Integer id) {
        Order order = orderService.getOrderById(id);
        model.addAttribute("order", order);
        model.addAttribute("orderDetailsList", orderService.getOrderById(id));
        return "order/confirmOrder";
    }

    @GetMapping("/order/confirm/vnpay-payment")
    public String GetMapping(HttpServletRequest request, Principal principal, Model model) {
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
        int orderId = vnPayService.orderReturn(request, user);
        model.addAttribute("order", orderService.getOrderById(orderId));
        model.addAttribute("orderDetailsList", orderService.getOrderById(orderId));
        return "order/confirmOrder";
    }


    @GetMapping("/makeCall/{orderId}")
    public String makeCall(HttpSession session, Principal principal, @PathVariable Integer orderId) {
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
        Order order = orderService.getOrderById(orderId);

        if (order.getOrderStatus().equals(OrderStatus.PROCESSING) || order.getOrderStatus().equals(OrderStatus.CANCELLED)) {
            return "redirect:/order/confirm/" + orderId;
        }
        countMakeCall++;
        if (countMakeCall > 3) {
            countMakeCall = 0;
            order.setOrderStatus(OrderStatus.CANCELLED);
            orderService.saveOrder(order);
            return "redirect:/order/confirm/" + orderId;
        }
        CallRequest callRequest = new CallRequest();

        // Generate random text (OTP)
        String randomText = generateRandomText(6);

        // Set the random text to the actions
        for (CallRequest.Action action : callRequest.getActions()) {
            if ("talk".equals(action.getAction())) {
                action.setText("Mã otp của bạn là: " + randomText + ".Nhắc lại, mã otp của bạn là: " + randomText);
            }
        }
        String phone = "84" + user.getPhone().substring(1);
        for (CallRequest.To to : callRequest.getTo()) {
            to.setNumber(phone);
            to.setAlias(user.getFullName());
        }

        session.setAttribute("generatedOtp", randomText.replaceAll(" ", ""));

        String url = "https://api.stringee.com/v1/call2/callout";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Accept", "application/json");

        headers.set("X-STRINGEE-AUTH", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiIsImN0eSI6InN0cmluZ2VlLWFwaTt2PTEifQ.eyJqdGkiOiJTS051dHJpbWVhbCIsImlzcyI6IlNLLjAucVNkd0R1RW1uQXdlZlA" +
                "5YWRGdmV1TVVwTUNMM2lrT0ciLCJleHAiOiIzMDAwMDAwMDAwMDAiLCJyZXN0X2FwaSI6dHJ1ZX0.YHmzsHKLB8sasXeboN2Bcsv4vh1M6Rf5_SJUI3yKrwc");


        HttpEntity<CallRequest> request = new HttpEntity<>(callRequest, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

        return "redirect:/confirmOTP/" + orderId;
    }

    @GetMapping("/confirmOTP/{orderId}")
    public String showConfirmOTPForm(Model model, @PathVariable Integer orderId) {
        Order order = orderService.getOrderById(orderId);
        if (order.getOrderStatus().equals(OrderStatus.PROCESSING) || order.getOrderStatus().equals(OrderStatus.CANCELLED)) {
            return "redirect:/order/confirm/" + orderId;
        }
        model.addAttribute("orderId", orderId);
        return "order/confirmOTP";
    }

    @PostMapping("/confirmOTP")
    public String confirmOTP(@ModelAttribute("otp") String otp, HttpSession session,
                             Model model, @RequestParam("orderId") Integer orderId) {
        // Retrieve the generated OTP from session
        String generatedOtp = (String) session.getAttribute("generatedOtp");

        Order order = orderService.getOrderById(orderId);

        model.addAttribute("order", order);
        model.addAttribute("orderDetailsList", orderService.getOrderById(orderId));

        // Compare the user input OTP with the generated OTP
        if (generatedOtp != null && generatedOtp.equals(otp.replaceAll(" ", ""))) {
            order.setOrderStatus(OrderStatus.PROCESSING);
            orderService.saveOrder(order);
            countMakeCall = 0;
            return "order/confirmOrder";
        } else if (countMakeCall > 3) {
            countMakeCall = 0;
            order.setOrderStatus(OrderStatus.CANCELLED);
            orderService.saveOrder(order);
            return "order/confirmOrder";
        } else {
            model.addAttribute("message", "Mã OTP không chính xác. Vui lòng thử lại.");
            model.addAttribute("countMakeCall", "Bạn còn " + (3 - countMakeCall) + " lần gửi mã OTP");
            model.addAttribute("orderId", orderId);
            return "order/confirmOTP";
        }
    }

    @GetMapping("/orders/status/{status}")
    public String getOrdersByStatus(@PathVariable String status, Model model, Principal principal) {
        User user;
        if (principal instanceof OAuth2AuthenticationToken) {
            boolean isOauth2User = principal instanceof OAuth2AuthenticationToken;
            model.addAttribute("isOauth2User", isOauth2User);
            OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) principal;
            OAuth2User oauthUser = token.getPrincipal();
            user = userService.findByEmail(oauthUser.getAttribute("email"));
        } else {
            model.addAttribute("isOauth2User", false);
            user = userService.findByUsername(principal.getName());
        }
        List<Order> orders = orderService.getOrdersByStatus(status);
        model.addAttribute("user", user);
        model.addAttribute("orders", orders);
        return "profile/order";
    }

    private String generateRandomText(int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(10); // Random number between 0-9
            text.append(number);
            if (i < length - 1) {
                text.append(" "); // Add spaces between numbers
            }
        }
        return text.toString();
    }

}
