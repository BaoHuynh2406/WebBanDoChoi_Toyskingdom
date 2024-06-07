package com.mts.toyskingdom.data.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class UserDTO {
    private int idUser;
    private String email;
    private String fullName;
    private String password;
    private String phoneNumber;
    private String address;
    @DateTimeFormat(pattern = "yyyy-MM-dd") // Định dạng ngày hợp lệ
    private Date birthday;
    private String role;
    private boolean active;
}