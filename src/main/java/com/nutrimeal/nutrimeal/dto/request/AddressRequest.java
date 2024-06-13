package com.nutrimeal.nutrimeal.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {

    private String fullName;
    private String phone;
    private Boolean gender;
    private String district;
    private String ward;
    private String apartmentNumber;
    private Boolean defaultAddress;
}
