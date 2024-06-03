package com.mts.toyskingdom.api;

import com.mts.toyskingdom.data.mgt.ResponseObject;
import com.mts.toyskingdom.service.UserSv;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
import java.util.function.Supplier;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api-public/user")
public class UserAPI {
    final UserSv userSv;

    @GetMapping("/getAllUsers")
    public ResponseEntity<ResponseObject<?>> doGetAllUsers() {
        return handleRequest(() -> {
            try {
                return userSv.getAllUser();
            } catch (SQLException e) {
                log.error("SQL error while fetching all users", e);
                return null;
            }
        }, "Lấy thông tin người dùng thành công", "Lấy thông tin người dùng thất bại");
    }

    @GetMapping("/getUserByEmail")
    public ResponseEntity<ResponseObject<?>> doGetFindEmail(@RequestParam("email") String email) {
        return handleRequest(() -> {
            try {
                return userSv.getUserByEmail(email);
            } catch (SQLException e) {
                log.error("SQL error while fetching user by email", e);
                return null;
            }
        }, "Lấy thông tin Email người dùng thành công", "Lấy thông tin Email người dùng thất bại");
    }

    @GetMapping("/getUserByidUser")
    public ResponseEntity<ResponseObject<?>> doGetFindidUser(@RequestParam("idUser") int idUser) {
        return handleRequest(() -> {
            try {
                return userSv.getUserByidUser(idUser);
            } catch (SQLException e) {
                log.error("SQL error while fetching user by ID", e);
                return null;
            }
        }, "Lấy thông tin người dùng thành công", "Lấy thông tin người dùng thất bại");
    }

    private ResponseEntity<ResponseObject<?>> handleRequest(Supplier<List<?>> supplier, String successMessage, String failureMessage) {
        var resultApi = new ResponseObject<>();
        try {
            List<?> data = supplier.get();
            if (data == null || data.isEmpty()) {
                resultApi.setSuccess(false);
                resultApi.setMessage(failureMessage);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resultApi);
            }
            resultApi.setData(data);
            resultApi.setSuccess(true);
            resultApi.setMessage(successMessage);
            return ResponseEntity.ok(resultApi);
        } catch (Exception e) {
            resultApi.setSuccess(false);
            resultApi.setMessage(failureMessage);
            log.error(failureMessage, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultApi);
        }
    }
}
