package com.mts.toyskingdom.api;


import com.mts.toyskingdom.data.dto.TestDTO;
import com.mts.toyskingdom.data.dto.UserRegistrationDto;
import com.mts.toyskingdom.data.mgt.ResponseObject;
import com.mts.toyskingdom.service.UserSv;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
}


