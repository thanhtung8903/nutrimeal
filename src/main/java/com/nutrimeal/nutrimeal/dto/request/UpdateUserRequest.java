package com.nutrimeal.nutrimeal.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {

    private String fullName;

    private String email;

    private String password;

    private String phone;

    private String avatar;

    private Boolean gender;

    private String image;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    private int point;
}

