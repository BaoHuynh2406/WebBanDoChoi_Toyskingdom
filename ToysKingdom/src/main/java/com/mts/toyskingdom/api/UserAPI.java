package com.mts.toyskingdom.api;

import com.mts.toyskingdom.data.mgt.ResponseObject;
import com.mts.toyskingdom.service.ProductSv;
import com.mts.toyskingdom.service.UserSv;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api-public/user")
public class UserAPI {
    final UserSv userSV;

    @GetMapping("/getAllUsers")
    public ResponseObject<?> doGetAllUsers() {
        var resultApi = new ResponseObject<>();
        try {
            resultApi.setData(userSV.getAllUser());
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
            resultApi.setData(userSV.getUserByEmail(email));
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
            resultApi.setData(userSV.getUserByidUser(idUser));
            resultApi.setSuccess(true);
            resultApi.setMessage("Lấy thông tin người dùng thành công");
        } catch (Exception e) {
            resultApi.setMessage("Lấy thông tin người dùng thất bại");
            log.error("Fail When Call API/api-public/user/getUserByidUser: ", e);
        }
        return resultApi;
    }
}
