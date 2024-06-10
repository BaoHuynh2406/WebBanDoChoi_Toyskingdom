package com.mts.toyskingdom.api;


import com.mts.toyskingdom.data.dto.OtpDTO;
import com.mts.toyskingdom.data.dto.UserLoginDTO;
import com.mts.toyskingdom.data.mgt.ResponseObject;
import com.mts.toyskingdom.service.SendEmailSv;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api-public/khoiphuctaikhoan")
public class KhoiPhucTaiKhoanAPI {
    final SendEmailSv sendEmailSv;


    @PostMapping("/getotp")
    public ResponseObject<?> doPostOTP(@RequestBody String email) {
        var resultApi = new ResponseObject<>();
        try {
            System.out.println(email);
            if (sendEmailSv.sendEmailOTP(email) != -1) {
                resultApi.setData("Đã gửi OTP");
                resultApi.setSuccess(true);
                resultApi.setMessage("Gửi thành công");
            } else {
                //Gửi lỗi từ service ra ví dụ sài email, email không hợp lệ ...
                resultApi.setData("Gửi otp that bai");
                resultApi.setSuccess(false);
                resultApi.setMessage("false");
            }
        } catch (Exception e) {
            resultApi.setSuccess(false);
            resultApi.setMessage("Lấy thông tin sản phẩm thất bại");
            log.error("Fail When Call API /api-public/products/getAllProducts : ", e);
        }
        return resultApi;
    }

    @PostMapping("/verify")
    public ResponseObject<?> verifyOTP(@RequestBody OtpDTO otpDTO) {
        var resultApi = new ResponseObject<>();
        try {
            System.out.println(otpDTO.toString());
            if (sendEmailSv.verifyOTP(otpDTO.getEmail(), otpDTO.getOtp(), otpDTO.getPassword())) {
                resultApi.setData("Đổi mật khẩu thành công.");
                resultApi.setSuccess(true);
                resultApi.setMessage("True");
            } else {
                //Gửi lỗi từ service ra ví dụ sài email, email không hợp lệ ...
                resultApi.setData("Mã OTP không hợp lệ.");
                resultApi.setSuccess(false);
                resultApi.setMessage("Sai otp");
            }
        } catch (Exception e) {
            resultApi.setSuccess(false);
            resultApi.setMessage("Lấy thông tin thất bại.");
            log.error("Fail When Call API /api-public/products/getAllProducts : ", e);
        }
        return resultApi;
    }
}
