package com.mts.toyskingdom.data.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserRegistrationDto {
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private Date birthday;
}
