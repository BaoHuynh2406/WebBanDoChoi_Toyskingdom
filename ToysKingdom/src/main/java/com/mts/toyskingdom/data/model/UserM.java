package com.mts.toyskingdom.data.model;

import com.mts.toyskingdom.data.entity.UserE;
import lombok.*;

<<<<<<< HEAD
import java.util.Date; // Thêm import này để sử dụng trường birthday
=======
import java.util.Date;
>>>>>>> admin_User
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserM {

    private int idUser;
    private String email;
    private String password;
    private String fullName;
    private String phoneNumber;
    private String address;
    private Date birthday;
    private String role;
    private boolean isActive;

    private String phoneNumber;
    private String address;
    private java.sql.Date birthday;
    private String role;
    private boolean active;

    public static UserM convertUserEToUserM(UserE userE) {
        return UserM.builder()
                .idUser(userE.getIdUser())
                .fullname(userE.getFullname())
                .password(userE.getPassword())
                .email(userE.getEmail())
                .password(userE.getPassword())
                .fullname(userE.getFullname())
                .phoneNumber(userE.getPhoneNumber())
                .address(userE.getAddress())
                .birthday(new java.sql.Date(userE.getBirthday().getTime()))
                .role(userE.getRole())
                .fullName(userE.getFullName())
                .phoneNumber(userE.getPhoneNumber())
                .address(userE.getAddress())
                .birthday(userE.getBirthday())
                .isActive(userE.isActive())
                .build();
    }

    public static List<UserM> convertListUserEToUserM(List<UserE> userEList) {
        return userEList.stream().map(userE -> convertUserEToUserM(userE)).collect(Collectors.toList());
    }
}
