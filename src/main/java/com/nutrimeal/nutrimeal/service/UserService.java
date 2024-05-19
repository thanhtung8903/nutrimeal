package com.nutrimeal.nutrimeal.service;


import com.nutrimeal.nutrimeal.dto.request.SignupRequest;
import com.nutrimeal.nutrimeal.model.Role;
import com.nutrimeal.nutrimeal.model.RoleName;
import com.nutrimeal.nutrimeal.model.User;
import com.nutrimeal.nutrimeal.repository.RoleRepository;
import com.nutrimeal.nutrimeal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public void signupUser(SignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email is already in use");
        } else {
            User user = new User();
            user.setFullName(request.getFullName());
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setPoint(0);
            user.setActive(true);

            Set<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByRoleName(RoleName.ROLE_CUSTOMER).orElseThrow(() -> new RuntimeException("Error: Role is not found")));
            user.setRoles(roles);

            userRepository.save(user);
        }

    }
}

