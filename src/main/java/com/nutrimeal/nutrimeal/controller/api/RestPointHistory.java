package com.nutrimeal.nutrimeal.controller.api;

import com.nutrimeal.nutrimeal.model.User;
import com.nutrimeal.nutrimeal.service.CustomOAuth2User;
import com.nutrimeal.nutrimeal.service.PointHistoryService;
import com.nutrimeal.nutrimeal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RestPointHistory {

    private final PointHistoryService pointHistoryService;
    private final UserService userService;

    private User UserAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user;

        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oauth2Token = (OAuth2AuthenticationToken) authentication;
            CustomOAuth2User oauthUser = (CustomOAuth2User) oauth2Token.getPrincipal();
            user = userService.findByEmail(oauthUser.getAttribute("email"));
        } else {
            user = userService.findByUsername(authentication.getName());
        }
        return user;
    }

    @GetMapping("/api/pointHistory")
    public ResponseEntity<?> getAllPointHistory() {
        User user = UserAuthentication();
        return ResponseEntity.ok(pointHistoryService.getPointHistoryByUser(user));
    }

}
