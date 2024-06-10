package com.nutrimeal.nutrimeal.controller;

import com.nutrimeal.nutrimeal.model.Order;
import com.nutrimeal.nutrimeal.model.OrderBasket;
import com.nutrimeal.nutrimeal.model.User;
import com.nutrimeal.nutrimeal.repository.ComboRepository;
import com.nutrimeal.nutrimeal.repository.OrderBasketRepository;
import com.nutrimeal.nutrimeal.repository.UserRepository;
import com.nutrimeal.nutrimeal.service.AddressService;
import com.nutrimeal.nutrimeal.service.ComboService;
import com.nutrimeal.nutrimeal.service.OrderBasketService;
import com.nutrimeal.nutrimeal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ShoppingCartController {

    private final OrderBasketService orderBasketService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final OrderBasketRepository orderBasketRepository;
    private final ComboService comboService;
    private final ComboRepository comboRepository;
    private final AddressService addressService;

    @GetMapping("/cart")
    public String showShoppingCart(Model model, Principal principal) {
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
        List<OrderBasket> orderBaskets = orderBasketService.findAllByUser(user);
        model.addAttribute("point", user.getPoint());
        model.addAttribute("orderBaskets", orderBaskets);
        return "order/cart";
    }


    @PostMapping("/update-quantity")
    @ResponseBody
    public ResponseEntity<String> updateQuantity(@RequestParam int productId, @RequestParam int quantity, Principal principal) {
        User user;
        if (principal instanceof OAuth2AuthenticationToken && principal != null) {
            OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) principal;
            OAuth2User oauthUser = token.getPrincipal();
            user = userRepository.findByEmail(oauthUser.getAttribute("email")).orElse(null);
        } else {
            user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));
        }
        OrderBasket orderBasket = orderBasketRepository.findByOrderBasketIdAndUser(productId, user);
        if (orderBasket != null) {
            orderBasket.setQuantity(quantity);
            orderBasketRepository.save(orderBasket);
            return ResponseEntity.ok("Quantity updated");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product not found in order basket");
        }
    }

    @GetMapping("/basket/remove/{oid}")
    public String removeProductFromBasket(@PathVariable("oid") Integer orderBasketId,
                                          Principal principal) {
        if (principal == null) {
            throw new RuntimeException("Bạn cần đăng nhập để xóa sản phẩm");
        }
        User user;
        if (principal instanceof OAuth2AuthenticationToken && principal != null) {
            OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) principal;
            OAuth2User oauthUser = token.getPrincipal();
            user = userService.findByEmail(oauthUser.getAttribute("email"));
        } else {
            user = userService.findByUsername(principal.getName());
        }

        if (user == null) {
            throw new RuntimeException("Bạn cần đăng nhập để xóa sản phẩm");
        }
        OrderBasket orderBasket = orderBasketRepository.findByOrderBasketIdAndUser(orderBasketId, user);
        orderBasketRepository.delete(orderBasket);
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model, Principal principal) {
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
        List<OrderBasket> orderBaskets = orderBasketService.findAllByUser(user);
        model.addAttribute("point", user.getPoint());
        model.addAttribute("orderBaskets", orderBaskets);
        model.addAttribute("address", addressService.findAllAddressByEmail(user.getEmail()));
        return "order/checkout";
    }
}