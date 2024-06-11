package com.nutrimeal.nutrimeal.controller.order;

import com.nutrimeal.nutrimeal.model.Order;
import com.nutrimeal.nutrimeal.model.User;
import com.nutrimeal.nutrimeal.repository.UserRepository;
import com.nutrimeal.nutrimeal.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final VNPayService vnPayService;
    private final UserRepository userRepository;


    @GetMapping("/order/confirm/{id}")
    public String confirmOrder(Model model, @PathVariable Integer id) {
        model.addAttribute("order", orderService.getOrderById(id));
        model.addAttribute("orderDetailsList", orderService.getOrderById(id));
        return "order/confirmOrder";
    }

    @GetMapping("/vnpay-payment")
    public String GetMapping(HttpServletRequest request, Model model){
        int paymentStatus =vnPayService.orderReturn(request);

        String orderInfo = request.getParameter("vnp_OrderInfo");
        String paymentTime = request.getParameter("vnp_PayDate");
        String transactionId = request.getParameter("vnp_TransactionNo");
        String totalPrice = request.getParameter("vnp_Amount");

        model.addAttribute("orderId", orderInfo);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("paymentTime", paymentTime);
        model.addAttribute("transactionId", transactionId);

        return paymentStatus == 1 ? "order/ordersuccess" : "order/orderfail";
    }

    @PostMapping("/submitOrder")
    public String submitOrder(@RequestParam("totalPrice") int totalPrice,
                              @RequestBody Order order,
                              HttpServletRequest request, Principal principal, Model model){
        if(principal == null) {
            return "redirect:/login";
        }
        User user;
        if (principal instanceof OAuth2AuthenticationToken && principal != null) {
            boolean isOauth2User = principal instanceof OAuth2AuthenticationToken && principal != null;
            model.addAttribute("isOauth2User", isOauth2User);
            OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) principal;
            OAuth2User oauthUser = token.getPrincipal();
            user = userRepository.findByEmail(oauthUser.getAttribute("email")).orElse(null);
        } else {
            model.addAttribute("isOauth2User", false);
            user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));
        }
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String vnpayUrl = vnPayService.createOrder(totalPrice, baseUrl, user);
        return "redirect:" + vnpayUrl;
    }

    @GetMapping("/submitOrder")
    public String home(){
        return "order/createorder";
    }
}
