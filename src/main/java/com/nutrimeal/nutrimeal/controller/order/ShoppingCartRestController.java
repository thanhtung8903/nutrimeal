package com.nutrimeal.nutrimeal.controller.order;

import com.nutrimeal.nutrimeal.model.Promotion;
import com.nutrimeal.nutrimeal.model.User;
import com.nutrimeal.nutrimeal.repository.PromotionRepository;
import com.nutrimeal.nutrimeal.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ShoppingCartRestController {

    private final UserService userService;
    private final OrderBasketService orderBasketService;
    private final OrderService orderService;
    private final PromotionService promotionService;
    private final UserPromotionService userPromotionService;

    @PostMapping("/basket/add/{cid}")
    public ResponseEntity<String> addProductToBasket(@PathVariable("cid") Integer comboId,
                                                     @RequestParam("day") String day,
                                                     Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(401).body("Bạn cần đăng nhập để thêm sản phẩm vào giỏ hàng");
        }
        User user;
        if (principal instanceof OAuth2AuthenticationToken token) {
            OAuth2User oauthUser = token.getPrincipal();
            user = userService.findByEmail(oauthUser.getAttribute("email"));
        } else {
            user = userService.findByUsername(principal.getName());
        }

        int dayByCombo = Integer.parseInt(day);

        int addedQuantity = orderBasketService.addComboToBasket(comboId, user, dayByCombo);
        if (addedQuantity >= 5) return ResponseEntity.ok("Bạn đã thêm tối đa 5 sản phẩm cùng loại vào giỏ hàng");

        return ResponseEntity.ok("Đã thêm sản phẩm vào giỏ hàng");
    }


    @PostMapping("/basket/update/{cid}/{qty}")
    public String updateQuantity(@PathVariable("cid") Integer comboId,
                                 @PathVariable("qty") Integer quantity,
                                 Principal principal) {
        if (principal == null) {
            return "Bạn cần đăng nhập để cập nhật số lượng";
        }
        User user;
        if (principal instanceof OAuth2AuthenticationToken token) {
            OAuth2User oauthUser = token.getPrincipal();
            user = userService.findByEmail(oauthUser.getAttribute("email"));
        } else {
            user = userService.findByUsername(principal.getName());
        }

        if (user == null) return "Bạn cần đăng nhập để cập nhật số lượng";

        int subtotal = orderBasketService.updateQuantity(quantity, comboId, user);

        return String.valueOf(subtotal);
    }

    @PostMapping("/order/create/{price}")
    public String createOrder(@PathVariable("price") Integer price, Principal principal) {
        if (principal == null) {
            return "Bạn cần đăng nhập để tạo đơn hàng";
        }
        User user;
        if (principal instanceof OAuth2AuthenticationToken token) {
            OAuth2User oauthUser = token.getPrincipal();
            user = userService.findByEmail(oauthUser.getAttribute("email"));
        } else {
            user = userService.findByUsername(principal.getName());
        }

        if (user == null) return "Bạn cần đăng nhập để tạo đơn hàng";

        orderService.createOrder(user, price);

        return "Đơn hàng của bạn đã được tạo thành công";
    }


    @PostMapping("/validate-discount-code")
    public ResponseEntity<?> validateDiscountCode(@RequestBody Map<String, String> payload, Principal principal) {
        User user;
        if (principal instanceof OAuth2AuthenticationToken token) {
            OAuth2User oauthUser = token.getPrincipal();
            user = userService.findByEmail(oauthUser.getAttribute("email"));
        } else {
            user = userService.findByUsername(principal.getName());
        }

        String code = payload.get("code");
        Optional<Promotion> discountCode = promotionService.findByPromotionCode(code);

        if (discountCode.isPresent() && discountCode.get().getPromotionStatus()) {
            if (!userPromotionService.existsByUserIdAndPromotionId(user.getUserId(), discountCode.get().getPromotionId())) {
                return ResponseEntity.ok(discountCode.get().getPromotionDiscount());
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Bạn đã sử dụng mã giảm giá này"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Không tìm thấy mã giảm giá này"));
        }
    }


}