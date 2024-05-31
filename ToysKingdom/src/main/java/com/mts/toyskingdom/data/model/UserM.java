package com.mts.toyskingdom.data.model;

import com.mts.toyskingdom.data.entity.UserE;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserM {

    private int idUser;
    private String password;
    private String email;
    private String role;


    public static UserM convertUserEToUserM(UserE userE) {
        return UserM.builder()
                .idUser(userE.getIdUser())
                .password(userE.getPassword())
                .email(userE.getEmail())
                .role(userE.getRole())
                .build();
    }

    public static List<UserM> convertListUserEToUserM(List<UserE> userEList) {
        return userEList.stream().map(userE -> convertUserEToUserM(userE)).collect(Collectors.toList());
    }
}