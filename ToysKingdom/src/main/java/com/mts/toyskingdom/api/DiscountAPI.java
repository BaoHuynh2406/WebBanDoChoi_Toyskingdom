package com.mts.toyskingdom.api;


import com.mts.toyskingdom.data.mgt.ResponseObject;
import com.mts.toyskingdom.service.DiscountSv;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api-public/discount")
public class DiscountAPI {

    final DiscountSv sv;

    @GetMapping("/getAll")
    public ResponseObject<?> doGetAll() {
        var resultApi = new ResponseObject<>();
        try {
            resultApi.setData(sv.getAllDiscount());
            resultApi.setSuccess(true);
            resultApi.setMessage("Lấy thông tin toàn bộ thành công");
        } catch (Exception e) {
            resultApi.setSuccess(false);
            resultApi.setMessage("Lấy thông tin thất bại");
            log.error("Fail When: ", e);
        }
        return resultApi;
    }

    @GetMapping("/getAllActive")
    public ResponseObject<?> doGetAllActive() {
        var resultApi = new ResponseObject<>();
        try {
            resultApi.setData(sv.getAllDiscountActive());
            resultApi.setSuccess(true);
            resultApi.setMessage("Lấy thông tin toàn bộ thành công");
        } catch (Exception e) {
            resultApi.setSuccess(false);
            resultApi.setMessage("Lấy thông tin thất bại");
            log.error("Fail When: ", e);
        }
        return resultApi;
    }
}
