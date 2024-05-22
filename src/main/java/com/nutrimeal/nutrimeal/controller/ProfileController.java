package com.nutrimeal.nutrimeal.controller;

import com.nutrimeal.nutrimeal.dto.request.AddressRequest;
import com.nutrimeal.nutrimeal.dto.request.ChangePasswordRequest;
import com.nutrimeal.nutrimeal.dto.request.UpdateUserRequest;
import com.nutrimeal.nutrimeal.model.Address;
import com.nutrimeal.nutrimeal.model.User;
import com.nutrimeal.nutrimeal.repository.AddressRepository;
import com.nutrimeal.nutrimeal.repository.UserRepository;
import com.nutrimeal.nutrimeal.service.AddressService;
import com.nutrimeal.nutrimeal.service.ImageUploadService;
import com.nutrimeal.nutrimeal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
    private final UserRepository userRepository;
    private final ImageUploadService imageUploadService;
    private final AddressService addressService;
    private final AddressRepository addressRepository;

    @GetMapping("/profile/account")
    public String profileAccount(Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("user", user);
        return "profile/account";
    }

    @PutMapping("/profile/account")
    public String updateProfile(
            @ModelAttribute UpdateUserRequest updateUserRequest,
            @RequestParam("imageUser") MultipartFile multipartFile,
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

    @GetMapping("/profile/addresslist")
    public String profileAddressList(Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));
        List<Address> addressList = addressService.findAllAddress(user.getUsername());
        model.addAttribute("user", user);
        model.addAttribute("addressList", addressList);
        return "profile/addresslist";
    }

    @GetMapping("/profile/address/add")
    public String profileAddress(Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));
        Address address = new Address();
        model.addAttribute("user", user);
        model.addAttribute("address", address);
        return "profile/address";
    }

    @GetMapping("/profile/address/update")
    public String updateAddress(Model model, Principal principal, @RequestParam("addressId") int addressId) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));
        Address address = addressRepository.findByAddressIdAndUser(addressId, user).orElseThrow(() -> new RuntimeException("Address not found"));
        model.addAttribute("address", address);
        return "profile/address";
    }

    @GetMapping("/profile/address/delete")
    public String deleteAddress(Principal principal, @RequestParam("addressId") int addressId) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));
        addressService.deleteAddress(addressId, user.getUsername());
        return "redirect:/profile/addresslist";
    }

    @PostMapping("/profile/saveAddress")
    public String saveAddress(Principal principal, @ModelAttribute("address") Address address){
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));

        // save the employee
        addressService.saveAddress(address, user.getUsername());

        // use a redirect to prevent duplicate submissions
        return "redirect:/profile/addresslist";
    }




    @GetMapping("/profile/order")
    public String profileOrder(Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("user", user);
        return "profile/order";
    }
}
