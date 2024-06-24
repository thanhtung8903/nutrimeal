package com.nutrimeal.nutrimeal.controller.api;

import com.nutrimeal.nutrimeal.dto.response.ShipperResponse;
import com.nutrimeal.nutrimeal.dto.response.UserInfoResponse;
import com.nutrimeal.nutrimeal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/shippers")
@RequiredArgsConstructor
public class RestShipper {

    private final UserService userService;

    @GetMapping()
    public ResponseEntity<List<ShipperResponse>> getAllShippers() {
        List<ShipperResponse> shippers = userService.findAllShipper();
        return ResponseEntity.ok(shippers);
    }
}
