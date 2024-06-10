package com.mts.toyskingdom.service;

import com.mts.toyskingdom.data.dto.UserDTO;
import com.mts.toyskingdom.data.model.UserM;

import java.sql.SQLException;
import java.util.List;

public interface UserSv {
    public List<UserM> getAllUser() throws SQLException;

    public List<UserM> getAllUserActive() throws SQLException;

    public List<UserM> getUserByEmail(String email) throws SQLException;

    public List<UserM> getUserByidUser(int idUser) throws SQLException;

    public int saveUser(UserDTO UserRegistration) throws SQLException;

    public int deleteUser(int idUser) throws SQLException;

    public int disableUser(int idUser) throws SQLException;
}
