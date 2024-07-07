package com.nutrimeal.nutrimeal.controller.api;

import com.nutrimeal.nutrimeal.model.User;
import com.nutrimeal.nutrimeal.service.PointHistoryService;
import com.nutrimeal.nutrimeal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RestPointHistory {

    private final PointHistoryService pointHistoryService;
    private final UserService userService;

    @GetMapping("/api/pointHistory")
    public ResponseEntity<?> getAllPointHistory() {
        var context = SecurityContextHolder.getContext();

        User user = userService.findByUsername(context.getAuthentication().getName());

        return ResponseEntity.ok(pointHistoryService.getPointHistoryByUser(user));
    }

}
