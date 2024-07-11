package com.nutrimeal.nutrimeal.controller.api;

import com.nutrimeal.nutrimeal.dto.response.UserInfoResponse;
import com.nutrimeal.nutrimeal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class RestUser {

    private final UserService userService;

    @GetMapping()
    public ResponseEntity<List<UserInfoResponse>> getAllUsers() {
        List<UserInfoResponse> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/roles")
    public ResponseEntity<?> updateUserRoles(@RequestParam String userId, @RequestParam String role) {
        userService.updateUserRoles(userId, role);
        return ResponseEntity.ok().build();

    }

}
