package com.nutrimeal.nutrimeal.email;

public interface EmailSender {
    void forgetPassword(String to, String userId, String token, String fullName);
}