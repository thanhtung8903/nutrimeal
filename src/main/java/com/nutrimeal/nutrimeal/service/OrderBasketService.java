package com.nutrimeal.nutrimeal.service;

import com.nutrimeal.nutrimeal.repository.ComboRepository;
import com.nutrimeal.nutrimeal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderBasketService {

    private final UserRepository userRepository;
    private final ComboRepository comboRepository;


}
