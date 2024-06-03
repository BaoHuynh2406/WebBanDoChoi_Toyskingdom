package com.mts.toyskingdom.data.model;

import com.mts.toyskingdom.data.entity.UserE;
import lombok.*;

import java.util.Date;
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


    public static UserM convertUserEToUserM(UserE userE) {
        return UserM.builder()
                .idUser(userE.getIdUser())
                .password(userE.getPassword())
                .email(userE.getEmail())
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