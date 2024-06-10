package com.nutrimeal.nutrimeal.controller.order;

import com.nutrimeal.nutrimeal.model.User;
import com.nutrimeal.nutrimeal.service.OrderBasketService;
import com.nutrimeal.nutrimeal.service.OrderService;
import com.nutrimeal.nutrimeal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class ShoppingCartRestController {

    private final UserService userService;
    private final OrderBasketService orderBasketService;
    private final OrderService orderService;

    @PostMapping("/basket/add/{cid}")
    public String addProductToBasket(@PathVariable("cid") Integer comboId,
                                     @RequestParam("day") String day,
                                     Principal principal) {
        if (principal == null) {
            return "Bạn cần đăng nhập để thêm sản phẩm vào giỏ hàng";
        }
        User user;
        if (principal instanceof OAuth2AuthenticationToken token) {
            OAuth2User oauthUser = token.getPrincipal();
            user = userService.findByEmail(oauthUser.getAttribute("email"));
        } else {
            user = userService.findByUsername(principal.getName());
        }

        if (user == null) return "Bạn cần đăng nhập để thêm sản phẩm vào giỏ hàng";

        int dayByCombo = Integer.parseInt(day);

        orderBasketService.addComboToBasket(comboId, user, dayByCombo);

        int addedQuantity = orderBasketService.addComboToBasket(comboId, user, dayByCombo);
        if(addedQuantity >= 5) return "Chỉ được thêm tối đa 5 sản phẩm cùng loại vào giỏ hàng";

        return  "Đã thêm sản phẩm vào giỏ hàng";
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

}