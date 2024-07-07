package com.nutrimeal.nutrimeal.controller;

import com.nutrimeal.nutrimeal.dto.request.ChangePasswordRequest;
import com.nutrimeal.nutrimeal.dto.request.UpdateUserRequest;
import com.nutrimeal.nutrimeal.model.Address;
import com.nutrimeal.nutrimeal.model.Order;
import com.nutrimeal.nutrimeal.model.OrderDetail;
import com.nutrimeal.nutrimeal.model.User;
import com.nutrimeal.nutrimeal.repository.AddressRepository;
import com.nutrimeal.nutrimeal.repository.UserRepository;
import com.nutrimeal.nutrimeal.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
//@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;
    private final ImageUploadService imageUploadService;
    private final AddressService addressService;
    private final OrderService orderService;
    private final OrderDetailService orderDetailService;

    @GetMapping("/profile/account")
    public String profileAccount(Model model, Principal principal) {
        if (principal instanceof OAuth2AuthenticationToken && principal != null) {
            boolean isOauth2User = principal instanceof OAuth2AuthenticationToken && principal != null;
            model.addAttribute("isOauth2User", isOauth2User);
            OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) principal;
            OAuth2User oauthUser = token.getPrincipal();
            User user = userService.findByEmail(oauthUser.getAttribute("email"));
            model.addAttribute("user", user);
            return "profile/account";
        }else{
            User user = userService.findByUsername(principal.getName());
            model.addAttribute("isOauth2User", false);
            model.addAttribute("user", user);
            return "profile/account";
        }
    }

    @PostMapping("/profile/account")
    public String updateProfile(
            @ModelAttribute UpdateUserRequest updateUserRequest,
            @RequestParam("imageuser") MultipartFile multipartFile,
            Principal principal) {
        try {
            User user;
            if (principal instanceof OAuth2AuthenticationToken && principal != null) {
                OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) principal;
                OAuth2User oauthUser = token.getPrincipal();
                user = userService.findByEmail(oauthUser.getAttribute("email"));
            } else {
                user = userService.findByUsername(principal.getName());
            }

            if (multipartFile != null && !multipartFile.isEmpty() && multipartFile.getBytes().length > 0) {
                String imageURL = imageUploadService.uploadFile(multipartFile);
                updateUserRequest.setImage(imageURL);
            } else {
                updateUserRequest.setImage(user.getImage()); // keep the old image if no new file is uploaded
            }
            userService.updateUser(updateUserRequest, user.getEmail());
            return "redirect:/profile/account?success=true";
        } catch (RuntimeException e) {
            return "redirect:/profile/account?error=true";
        } catch (IOException e) {
            return "redirect:/profile/account?error=true";
        }
    }

    @GetMapping("/profile/password")
    public String profilePassword(Model model, Principal principal) {
        User user;
        if (principal instanceof OAuth2AuthenticationToken && principal != null) {
            boolean isOauth2User = principal instanceof OAuth2AuthenticationToken && principal != null;
            model.addAttribute("isOauth2User", isOauth2User);
            OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) principal;
            OAuth2User oauthUser = token.getPrincipal();
            user = userService.findByEmail(oauthUser.getAttribute("email"));
        } else {
            model.addAttribute("isOauth2User", false);
            user = userService.findByUsername(principal.getName());
        }
        model.addAttribute("user", user);
        return "profile/password";
    }

    @PostMapping("/profile/password")
    public String changePassword(@ModelAttribute("ChangePassword") ChangePasswordRequest changePasswordRequest, Model model, Principal principal) {
        try {
            User user;
            if (principal instanceof OAuth2AuthenticationToken && principal != null) {
                OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) principal;
                OAuth2User oauthUser = token.getPrincipal();
                user = userService.findByEmail(oauthUser.getAttribute("email"));
            } else {
                user = userService.findByUsername(principal.getName());
            }
            userService.changePassword(changePasswordRequest, user.getUsername());
            return "redirect:/profile/password?success=true";
        } catch (RuntimeException e) {
            return "redirect:/profile/password?error=true";
        }
    }

    @GetMapping("/profile/point")
    public String profilePoint(Model model, Principal principal) {
        User user;
        if (principal instanceof OAuth2AuthenticationToken && principal != null) {
            boolean isOauth2User = principal instanceof OAuth2AuthenticationToken && principal != null;
            model.addAttribute("isOauth2User", isOauth2User);
            OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) principal;
            OAuth2User oauthUser = token.getPrincipal();
            user = userService.findByEmail(oauthUser.getAttribute("email"));
        } else {
            model.addAttribute("isOauth2User", false);
            user = userService.findByUsername(principal.getName());
        }
        model.addAttribute("user", user);
        model.addAttribute("point", user.getPoint());
        return "profile/point";
    }

    @GetMapping("/profile/address")
    public String profileAddress(Model model, Principal principal) {
        User user;
        if (principal instanceof OAuth2AuthenticationToken && principal != null) {
            boolean isOauth2User = principal instanceof OAuth2AuthenticationToken && principal != null;
            model.addAttribute("isOauth2User", isOauth2User);
            OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) principal;
            OAuth2User oauthUser = token.getPrincipal();
            user = userService.findByEmail(oauthUser.getAttribute("email"));
        } else {
            model.addAttribute("isOauth2User", false);
            user = userService.findByUsername(principal.getName());
        }
        List<Address> addressList = addressService.findAllAddressByEmail(user.getEmail());
        model.addAttribute("user", user);
        model.addAttribute("addressList", addressList);
        return "profile/address";
    }

    @PostMapping("/profile/address")
    public String profileAddressAdd(@ModelAttribute Address address, Principal principal) {
        User user;
        if (principal instanceof OAuth2AuthenticationToken && principal != null) {
            OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) principal;
            OAuth2User oauthUser = token.getPrincipal();
            user = userService.findByEmail(oauthUser.getAttribute("email"));
        } else {
            user = userService.findByUsername(principal.getName());
        }
        address.setIsActive(true);
        addressService.saveAddress(address, user.getEmail());
        return "redirect:/profile/address";
    }

    @PostMapping("/profile/address/edit")
    public String profileAddressEdit(@ModelAttribute Address address, Model model, Principal principal) {
        User user;
        if (principal instanceof OAuth2AuthenticationToken && principal != null) {
            OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) principal;
            OAuth2User oauthUser = token.getPrincipal();
            user = userService.findByEmail(oauthUser.getAttribute("email"));
        } else {
            user = userService.findByUsername(principal.getName());
        }
        addressService.updateAddress(address, user.getEmail());
        return "redirect:/profile/address";
    }

    @GetMapping("/profile/address/setdefault/{addressId}")
    public String profileAddressSetDefault(@PathVariable Integer addressId, Model model, Principal principal) {
        User user;
        if (principal instanceof OAuth2AuthenticationToken && principal != null) {
            OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) principal;
            OAuth2User oauthUser = token.getPrincipal();
            user = userService.findByEmail(oauthUser.getAttribute("email"));
        } else {
            model.addAttribute("isOauth2User", false);
            user = userService.findByUsername(principal.getName());
        }
        addressService.setDefaultAddress(addressId, user.getEmail());
        return "redirect:/profile/address";
    }

    @GetMapping("/profile/address/delete/{addressId}")
    public String profileAddressDelete(@PathVariable Integer addressId, Model model, Principal principal) {
        User user;
        if (principal instanceof OAuth2AuthenticationToken && principal != null) {
            OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) principal;
            OAuth2User oauthUser = token.getPrincipal();
            user = userService.findByEmail(oauthUser.getAttribute("email"));
        } else {
            user = userService.findByUsername(principal.getName());
        }
        addressService.deleteAddress(addressId, user.getEmail());
        return "redirect:/profile/address";
    }

    @GetMapping("/profile/order")
    public String profileOrder(Model model, Principal principal) {
        User user;
        if (principal instanceof OAuth2AuthenticationToken && principal != null) {
            boolean isOauth2User = principal instanceof OAuth2AuthenticationToken && principal != null;
            model.addAttribute("isOauth2User", isOauth2User);
            OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) principal;
            OAuth2User oauthUser = token.getPrincipal();
            user = userService.findByEmail(oauthUser.getAttribute("email"));
        } else {
            model.addAttribute("isOauth2User", false);
            user = userService.findByUsername(principal.getName());
        }
        model.addAttribute("user", user);
        return "profile/order";
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
        List<Order> orders = orderService.getOrdersByStatusAndUser(status, user);
        model.addAttribute("user", user);
        model.addAttribute("orders", orders);
        return "profile/order";
    }

    @GetMapping("/orderdetail/{orderId}")
    public String orderDetail(@PathVariable("orderId") int orderId, Model model, Principal principal) {
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
        Order order = orderService.getOrderById(orderId);
        List<OrderDetail> orderDetails = orderDetailService.getOrderDetailsByOrder(order);
        model.addAttribute("order", order);
        model.addAttribute("orderDetails", orderDetails);
        model.addAttribute("user", user);
        return "profile/orderDetail";
    }

}