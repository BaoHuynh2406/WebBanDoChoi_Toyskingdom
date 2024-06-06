package com.mts.toyskingdom.api;


import com.mts.toyskingdom.data.dto.TestDTO;
import com.mts.toyskingdom.data.dto.UserLoginDTO;
import com.mts.toyskingdom.data.dto.UserRegistrationDto;
import com.mts.toyskingdom.data.mgt.ResponseObject;
import com.mts.toyskingdom.data.model.UserM;
import com.mts.toyskingdom.service.UserSv;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api-public/user")
public class UserAPI {
    final UserSv userSv;

    @GetMapping("/getAllUsers")
    public ResponseObject<?> doGetAllUsers() {
        var resultApi = new ResponseObject<>();
        try {
            resultApi.setData(userSv.getAllUser());
            resultApi.setSuccess(true);
            resultApi.setMessage("Lấy thông tin người dùng thành công");
        } catch (Exception e) {
            resultApi.setSuccess(false);
            resultApi.setMessage("Lấy thông tin người dùng thất bại");
            log.error("Fail When Call API/api-public/user/getAllUsers : ", e);
        }
        return resultApi;
    }
    @GetMapping("/getUserByEmail")
    public ResponseObject<?> doGetFindEmail(@RequestParam("email") String email) {
        var resultApi = new ResponseObject<>();
        try {
            resultApi.setData(userSv.getUserByEmail(email));
            resultApi.setSuccess(true);
            resultApi.setMessage("Lấy thông tin Email người dùng thành công");
        } catch (Exception e) {
            resultApi.setMessage("Lấy thông tin Email người dùng thất bại");
            log.error("Fail When Call API/api-public/user/getFindEmail : ", e);
        }
        return resultApi;
    }
    @GetMapping("/getUserByidUser")
    public ResponseObject<?> doGetFindidUser(@RequestParam("idUser") int idUser) {
        var resultApi = new ResponseObject<>();
        try {
            resultApi.setData(userSv.getUserByidUser(idUser));
            resultApi.setSuccess(true);
            resultApi.setMessage("Lấy thông tin người dùng thành công");
        } catch (Exception e) {
            resultApi.setMessage("Lấy thông tin người dùng thất bại");
            log.error("Fail When Call API/api-public/user/getUserByidUser: ", e);
        }
        return resultApi;
    }

    // hàm checkUserLogin kiểm tra quá trình đăng nhập
    @PostMapping("/checkUserLogin")
    public ResponseEntity<ResponseObject<?>> doCheckUserLogin(@RequestBody UserLoginDTO loginRequest) {
        var resultApi = new ResponseObject<>();
        try {
            List<UserM> users = userSv.getUserByEmail(loginRequest.getEmail());
            if (users == null || users.isEmpty()) {
                resultApi.setSuccess(false);
                resultApi.setMessage("Email không tồn tại");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resultApi);
            }
            UserM user = users.get(0);
            if (user.getPassword().equals(loginRequest.getPassword())) {
                resultApi.setSuccess(true);
                resultApi.setMessage("Đăng nhập thành công");
                resultApi.setData(List.of(user));
                return ResponseEntity.ok(resultApi);
            } else {
                resultApi.setSuccess(false);
                resultApi.setMessage("Mật khẩu không đúng");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resultApi);
            }
        } catch (SQLException e) {
            resultApi.setSuccess(false);
            resultApi.setMessage("Đã xảy ra lỗi");
            log.error("SQL error while checking user login", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultApi);
        }}

    @PostMapping("/register")
    public ResponseObject<?> registerUser( UserRegistrationDto userRegistrationDto) {
        var resultApi = new ResponseObject<>();
        try {
            int rowsAffected = userSv.insertUser(userRegistrationDto);
            if (rowsAffected > 0) {
                resultApi.setSuccess(true);
                resultApi.setMessage("Đăng ký người dùng thành công");
            } else {
                resultApi.setSuccess(false);
                resultApi.setMessage("Đăng ký người dùng thất bại");
            }
        } catch (Exception e) {
            resultApi.setSuccess(false);
            resultApi.setMessage("Đăng ký người dùng thất bại");
            log.error("Fail when calling API to register user: ", e);
        }
        return resultApi;
    }

    @PostMapping("/update")
    public ResponseObject<?> updateUser( UserRegistrationDto userRegistrationDto) {
        var resultApi = new ResponseObject<>();
        try {
            int rowsAffected = userSv.updateUser(userRegistrationDto);
            if (rowsAffected > 0) {
                resultApi.setSuccess(true);
                resultApi.setMessage("Cập nhật người dùng thành công");
            } else {
                resultApi.setSuccess(false);
                resultApi.setMessage("Cập nhật người dùng thất bại");
            }
        } catch (Exception e) {
            resultApi.setSuccess(false);
            resultApi.setMessage("Cập nhật người dùng thất bại");
            log.error("Fail when calling API to update user: ", e);
        }
        return resultApi;
    }

    @DeleteMapping("/delete/{idUser}")
    public ResponseObject<?> deleteUser(@PathVariable int idUser) {
        var resultApi = new ResponseObject<>();
        try {
            int rowsAffected = userSv.deleteUser(idUser);
            if (rowsAffected > 0) {
                resultApi.setSuccess(true);
                resultApi.setMessage("Xóa người dùng thành công");
            } else {
                resultApi.setSuccess(false);
                resultApi.setMessage("Xóa người dùng thất bại");
            }
        } catch (Exception e) {
            resultApi.setSuccess(false);
            resultApi.setMessage("Xóa người dùng thất bại");
            log.error("Fail when calling API to delete user: ", e);
        }
        return resultApi;
    }

}


