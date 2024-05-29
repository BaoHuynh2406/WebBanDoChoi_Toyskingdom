//package com.mts.ToysKingdom.api;
//
//import com.church.gxtanhung.systemconfigsecurity.security.UserPrincipal;
//import com.church.gxtanhung.tanhung.constant.ApiMessage;
//import com.church.gxtanhung.tanhung.constant.ChangePasswordMessage;
//import com.church.gxtanhung.tanhung.data.dto.TaiKhoanDto;
//import com.church.gxtanhung.tanhung.data.mgt.ResponseObject;
//import com.church.gxtanhung.tanhung.service.TaiKhoanService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@Slf4j
//@RequiredArgsConstructor
//@RequestMapping("/api-public/taikhoan")
//public class TaiKhoanApi {
//    final TaiKhoanService taiKhoanService;
//
//    @GetMapping("/getAllTaiKhoan")
//    public ResponseObject<?> doGetListQuanLyTaiKhoan() {
//        var resultApi = new ResponseObject<>();
//        try {
//            resultApi.setData(taiKhoanService.getAllTaiKhoan());
//            resultApi.setSuccess(true);
//            resultApi.setMessage("Lấy thông tin tài khoản thành công");
//        } catch (Exception e) {
//            resultApi.setSuccess(false);
//            resultApi.setMessage("Lấy thông tin tài khoản thất bại");
//            log.error("Fail When Call API /api-public/taikhoan/getAllTaiKhoan : ", e);
//        }
//        return resultApi;
//    }
//
//    @GetMapping("/getTaiKhoan")
//    public ResponseObject<?> getTaiKhoan(@AuthenticationPrincipal UserPrincipal userPrincipal) {
//        var resultApi = new ResponseObject<>();
//        try {
//            resultApi.setData(taiKhoanService.getTaiKhoanByUserId(userPrincipal.getUserId()));
//            resultApi.setSuccess(true);
//            resultApi.setMessage(ApiMessage.BasicMessageApi.SUCCESS.getBasicMessageApi());
//        } catch (Exception e) {
//            resultApi.setSuccess(false);
//            resultApi.setMessage(ApiMessage.BasicMessageApi.FAIL.getBasicMessageApi());
//            log.error("Fail When Call API /api-public/taikhoan/getTaiKhoan :  ", e);
//        }
//        return resultApi;
//    }
//
//    @PostMapping("/updateTaiKhoan")
//    public ResponseObject<?> doPostSaveTaiKhoan(@RequestBody TaiKhoanDto taiKhoanDto) {
//        var resultApi = new ResponseObject<>();
//        try {
//            resultApi.setData(taiKhoanService.updateTaiKhoan(taiKhoanDto));
//            resultApi.setSuccess(true);
//            resultApi.setMessage(ApiMessage.BasicMessageApi.SUCCESS.getBasicMessageApi());
//        } catch (Exception e) {
//            resultApi.setSuccess(false);
//            resultApi.setMessage(ApiMessage.BasicMessageApi.FAIL.getBasicMessageApi());
//            log.error("Fail When Call API /api-public/taikhoan/updateTaiKhoan : ", e);
//        }
//        return resultApi;
//    }
//
//    @PostMapping("/changePassword")
//    public ResponseObject<?> doPostChangePasswordOnTaiKhoan(
//            @RequestBody TaiKhoanDto taiKhoanDto,
//            @AuthenticationPrincipal UserPrincipal userPrincipal
//    ) {
//        var resultApi = new ResponseObject<>();
//        try {
//            var result = taiKhoanService.changePasswordByUserId(taiKhoanDto, userPrincipal);
//            resultApi.setData(result.get("rowsEffected"));
//            resultApi.setSuccess(result.get("status").equals(ChangePasswordMessage.SUCCEED.getMessage()));
//            resultApi.setMessage((String) result.get("status"));
//        } catch (Exception e) {
//            resultApi.setSuccess(false);
//            resultApi.setMessage(ApiMessage.BasicMessageApi.FAIL.getBasicMessageApi());
//            log.error("Fail When Call API /api-public/taikhoan/changePassword :  ", e);
//        }
//        return resultApi;
//    }
//
//    @PostMapping("/deleteTaiKhoan")
//    public ResponseObject<?> doPostDeleteTaiKhoan(@RequestBody TaiKhoanDto taiKhoanDto) {
//        var resultApi = new ResponseObject<>();
//        try {
//            resultApi.setData(taiKhoanService.deleteTaiKhoan(taiKhoanDto));
//            resultApi.setSuccess(true);
//            resultApi.setMessage("Xóa tài khoản thành công");
//        } catch (Exception e) {
//            resultApi.setSuccess(false);
//            resultApi.setMessage("Xóa tài khoản thất bại");
//            log.error("Fail When Call API /api-public/taikhoan/deleteTaiKhoan : ", e);
//        }
//        return resultApi;
//    }
//
//}
