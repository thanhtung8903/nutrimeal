package com.nutrimeal.nutrimeal.service;

import com.nutrimeal.nutrimeal.repository.ForgetTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ForgetTokenService {

    private final ForgetTokenRepository forgetTokenRepository;
}
