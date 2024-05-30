package com.mts.toyskingdom.service.impl;

import com.mts.toyskingdom.data.entity.UserE;
import com.mts.toyskingdom.data.model.ProductM;
import com.mts.toyskingdom.data.model.UserM;
import com.mts.toyskingdom.mapper.UserMapper;
import com.mts.toyskingdom.service.UserSv;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
@Service
@RequiredArgsConstructor
public class UserSvlmpl implements UserSv {
final UserMapper userMapper;
    @Override
    public List<UserM> getAllUser()  throws SQLException{
        var listResultEntity = userMapper.getAllUser();
        if (Objects.nonNull(listResultEntity)) {
            return UserM.convertListUserEToUserM(listResultEntity);
        }
        return null;
    }
    @Override
    public List<UserM> getUserByEmail(String email)  throws SQLException{
        var listResultEntity = userMapper.getUserByEmail(email);
        if (Objects.nonNull(listResultEntity)) {
            return UserM.convertListUserEToUserM(listResultEntity);
        }
        return null;
    }
}
