package com.mts.toyskingdom.api;


import com.mts.toyskingdom.data.dto.UserLoginDTO;
import com.mts.toyskingdom.data.dto.UserDTO;
import com.mts.toyskingdom.data.mgt.ResponseObject;
import com.mts.toyskingdom.data.model.UserM;
import com.mts.toyskingdom.service.UserSv;
import jakarta.servlet.http.HttpSession;
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


    @GetMapping("/getAllUsersActive")
    public ResponseObject<?> doGetAllUsersActive() {
        var resultApi = new ResponseObject<>();
        try {
            resultApi.setData(userSv.getAllUserActive());
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
    public ResponseObject<?> doCheckUserLogin(@RequestBody UserLoginDTO loginRequest, HttpSession session) {
        var resultApi = new ResponseObject<>();
        try {
            List<UserM> users = userSv.getUserByEmail(loginRequest.getEmail());
            if (users == null || users.isEmpty()) {
                resultApi.setSuccess(false);
                resultApi.setMessage("Email không tồn tại");
                return resultApi;
            }
            UserM user = users.get(0);
            if (user.getPassword().equals(loginRequest.getPassword())) {
                resultApi.setSuccess(true);

                session.setAttribute("user", user);
                System.out.println(user.getFullName());

                resultApi.setMessage("Đăng nhập thành công");
                resultApi.setData(List.of(user));
            } else {
                resultApi.setSuccess(false);
                resultApi.setMessage("Mật khẩu không đúng");
            }
        } catch (SQLException e) {
            resultApi.setSuccess(false);
            resultApi.setMessage("Đã xảy ra lỗi");
            log.error("SQL error while checking user login", e);
        }
        return resultApi;
    }

    @PostMapping("/save")
    public ResponseObject<?> add(@RequestBody UserDTO userDTO) {
        var resultApi = new ResponseObject<>();
        try {
            int ketQua = userSv.saveUser(userDTO);
            //Không có gì để lưu hoặc cập nhật
            if (ketQua == 0) {
                resultApi.setSuccess(false);
                resultApi.setMessage("Không có gì để cập nhật hoặc thêm");
            } else if (ketQua == 1) { // 1 khi lưu thành công
                resultApi.setSuccess(true);
                resultApi.setMessage("Thêm mới thành công");
            } else { // 2 khi cập nhật thành công
                resultApi.setSuccess(true);
                resultApi.setMessage("Cập nhật thành công");
            }
        } catch (Exception e) {
            resultApi.setSuccess(false);
            resultApi.setMessage("Lỗi save");
            log.error("Fail when: ", e);
        }
        return resultApi;
    }

    @DeleteMapping("/delete")
    public ResponseObject<?> delete(@RequestParam int idUser) {
        var resultApi = new ResponseObject<>();
        try {
            int ketQua = userSv.deleteUser(idUser);
            if (ketQua > 0) {
                resultApi.setSuccess(true);
                resultApi.setMessage("Đã xóa thành công");
            } else { //Id không tồn tại
                resultApi.setSuccess(false);
                resultApi.setMessage("Id không tồn tại");
            }
        } catch (Exception e) {
            resultApi.setSuccess(false);
            resultApi.setMessage("Lỗi");
            log.error("Fail when: ", e);
        }
        return resultApi;
    }

    @DeleteMapping("/disable")
    public ResponseObject<?> disable(@RequestParam int idUser) {
        var resultApi = new ResponseObject<>();
        try {
            int ketQua = userSv.disableUser(idUser);
            if (ketQua > 0) {
                resultApi.setSuccess(true);
                resultApi.setMessage("Đã xóa thành công");
            } else { //Id không tồn tại
                resultApi.setSuccess(false);
                resultApi.setMessage("Id không tồn tại");
            }
        } catch (Exception e) {
            resultApi.setSuccess(false);
            resultApi.setMessage("Lỗi");
            log.error("Fail when: ", e);
        }
        return resultApi;
    }


}


