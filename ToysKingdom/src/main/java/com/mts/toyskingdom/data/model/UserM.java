package com.mts.toyskingdom.data.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mts.toyskingdom.data.entity.UserE;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date birthday;
    private String role;
    private boolean active;

    public static UserM convertUserEToUserM(UserE userE) {
        return UserM.builder()
                .idUser(userE.getIdUser())
                .fullName(userE.getFullName())
                .password(userE.getPassword())
                .email(userE.getEmail())
                .address(userE.getAddress())
                .birthday(userE.getBirthday())
                .phoneNumber(userE.getPhoneNumber())
                .role(userE.getRole())
                .active(userE.isActive())
                .build();
    }

    public static List<UserM> convertListUserEToUserM(List<UserE> userEList) {
        return userEList.stream().map(userE -> convertUserEToUserM(userE)).collect(Collectors.toList());
    }
}
