package com.nutrimeal.nutrimeal.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private Date dob;

    private int point;
}

