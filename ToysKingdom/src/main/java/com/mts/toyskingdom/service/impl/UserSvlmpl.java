package com.mts.toyskingdom.service.impl;

import com.mts.toyskingdom.data.dto.UserRegistrationDto;
import com.mts.toyskingdom.data.entity.UserE;
import com.mts.toyskingdom.data.model.UserM;
import com.mts.toyskingdom.mapper.UserMapper;
import com.mts.toyskingdom.service.UserSv;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserSvlmpl implements UserSv {
    final UserMapper userMapper;

    @Override
    public List<UserM> getUserByidUser(int idUser) {
        return convertList(userMapper.getUserByidUser(idUser));
    }

    @Override
    public List<UserM> getAllUser() {
        return convertList(userMapper.getAllUser());
    public int insertUser(UserRegistrationDto userRegistration) throws SQLException {
        return userMapper.insertUser(userRegistration);
    }

    @Override
    public List<UserM> getAllUser()  throws SQLException{
        var listResultEntity = userMapper.getAllUser();
        if (Objects.nonNull(listResultEntity)) {
            return UserM.convertListUserEToUserM(listResultEntity);
        }
        return null;
    }

    @Override
    public List<UserM> getUserByEmail(String email) {
        return convertList(userMapper.getUserByEmail(email));
    }

    private List<UserM> convertList(List<UserE> listResultEntity) {
        return Optional.ofNullable(listResultEntity)
                .map(UserM::convertListUserEToUserM)
                .orElse(null);
    }
}
