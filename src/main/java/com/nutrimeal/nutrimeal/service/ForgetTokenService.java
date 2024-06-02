package com.nutrimeal.nutrimeal.service;

import com.nutrimeal.nutrimeal.model.ForgetToken;
import com.nutrimeal.nutrimeal.model.User;
import com.nutrimeal.nutrimeal.repository.ForgetTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ForgetTokenService {

    private final ForgetTokenRepository forgetTokenRepository;

    public void createOrUpdateForgetToken(User user) {
        ForgetToken forgetToken = forgetTokenRepository.findByUser(user);
        if (forgetToken == null) {
            forgetToken = new ForgetToken();
            forgetToken.setUser(user);
        }
        forgetToken.setForgetToken(UUID.randomUUID().toString());
        forgetToken.setForgetTokenCreated(LocalDateTime.now());
        forgetToken.setForgetTokenExpired(LocalDateTime.now().plusHours(1));
        forgetTokenRepository.save(forgetToken);
    }

    public void deleteForgetToken(User user) {
        ForgetToken forgetToken = forgetTokenRepository.findByUser(user);
        forgetTokenRepository.delete(forgetToken);
    }

    public String getForgetTokenByUser(User user) {
        ForgetToken forgetToken = forgetTokenRepository.findByUser(user);
        return forgetToken.getForgetToken();
    }

    public ForgetToken getForgetTokenObjByUser(User user) {
        return forgetTokenRepository.findByUser(user);
    }
}
