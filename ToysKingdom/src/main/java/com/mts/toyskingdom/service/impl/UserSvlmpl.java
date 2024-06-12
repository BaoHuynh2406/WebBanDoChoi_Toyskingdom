package com.mts.toyskingdom.service.impl;

import com.mts.toyskingdom.data.dto.UserDTO;
import com.mts.toyskingdom.data.entity.UserE;
import com.mts.toyskingdom.data.model.UserM;
import com.mts.toyskingdom.mapper.UserMapper;
import com.mts.toyskingdom.service.UserSv;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserSvlmpl implements UserSv {
    final UserMapper userMapper;


    //    Lấy user bằng ID
    @Override
    public List<UserM> getUserByidUser(int idUser) {
        return convertList(userMapper.getUserByidUser(idUser));
    }


    @Override
    public int saveUser(UserDTO userDTO) throws SQLException {
        // Kiểm tra nếu user neu chua co thi insert user
        if (userMapper.getUserByEmail(userDTO.getEmail()).isEmpty()) {
            // Thêm mới
            userDTO.setActive(true);
            //Trả về 1 có nghĩa là thêm thành công
            if (userMapper.insertUser(userDTO) > 0) return 1;
        } else {
            // Cập nhật
            userDTO.setActive(true);
            //Trả về 2 có nghĩa cập nhật thành công
            if (userMapper.updateUser(userDTO) > 0) return 2;
        }
        return 0;
    }

    @Override
    public int deleteUser(int idUser) throws SQLException {
        return userMapper.deleteUser(idUser);
    }

    @Override
    public int disableUser(int idUser) throws SQLException {
        return userMapper.disableUser(idUser);
    }


    public int insertUser(UserDTO userRegistration) throws SQLException {
        return userMapper.insertUser(userRegistration);
    }

    @Override
    public List<UserM> getAllUser() throws SQLException {
        var listResultEntity = userMapper.getAllUser();
        if (Objects.nonNull(listResultEntity)) {
            return UserM.convertListUserEToUserM(listResultEntity);
        }
        return null;
    }

    @Override
    public List<UserM> getAllUserActive() throws SQLException {
        var listResultEntity = userMapper.getAllUserActive();
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
