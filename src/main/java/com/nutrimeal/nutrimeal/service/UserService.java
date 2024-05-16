package com.nutrimeal.nutrimeal.service;


import com.nutrimeal.nutrimeal.dto.request.SignupRequest;
import com.nutrimeal.nutrimeal.model.User;
import com.nutrimeal.nutrimeal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //    saveUser method is missing
    public void saveUser(SignupRequest request) {
        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        userRepository.save(user);
    }

}