package com.mts.toyskingdom.data.entity;

import lombok.Data;

import java.util.Date;
@Data
public class UserE {
    private int idUser;
    private String email;
    private String password;
    private String fullName;
    private String phoneNumber;
    private String address;
    private Date birthday;
    private String role;
    private boolean isActive;
}
