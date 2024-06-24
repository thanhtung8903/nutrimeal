package com.nutrimeal.nutrimeal.controller.api;

import com.nutrimeal.nutrimeal.model.Address;
import com.nutrimeal.nutrimeal.model.User;
import com.nutrimeal.nutrimeal.service.AddressService;
import com.nutrimeal.nutrimeal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
@RequiredArgsConstructor
public class RestAddress {

    private final AddressService addressService;

    private final UserService userService;

    @GetMapping("/{userId}")
    public List<Address> getAddressByUser(@PathVariable String userId) {
        User user = userService.getUserById(userId);
        return addressService.findAllAddressByEmail(user.getEmail());
    }

}
