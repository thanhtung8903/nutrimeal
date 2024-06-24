package com.nutrimeal.nutrimeal.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {

    private String username;
    private String fullName;
    private String email;
    private String password;
    private String phoneNumber;
    private String gender;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;
}