package com.nutrimeal.nutrimeal.dto.response;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoResponse {
    private String userId;
    private String email;
    private String fullName;
    private String phone;
    private String avatar;
    private Boolean gender;
    private Date dob;
    private int point;
    private List<String> roles;
    private String tokenType;
    private String accessToken;

}