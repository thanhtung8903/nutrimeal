package com.nutrimeal.nutrimeal.service;

import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;
import java.util.stream.Collectors;

public class CustomOAuth2User extends UserDetailsImpl implements OAuth2User {

    private final Map<String, Object> attributes;

    public CustomOAuth2User(UserDetailsImpl userDetails, Map<String, Object> attributes) {
        super(userDetails.getId(), userDetails.getUsername(), userDetails.getPassword(), userDetails.getFullName(),
                userDetails.getImage(), userDetails.getAuthorities().stream().collect(Collectors.toList()), userDetails.isEnabled());
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getName() {
        return this.getUsername();
    }
}