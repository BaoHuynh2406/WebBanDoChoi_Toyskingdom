package com.mts.toyskingdom.api;


import com.mts.toyskingdom.data.dto.DiscountDTO;
import com.mts.toyskingdom.data.dto.ProductDTO;
import com.mts.toyskingdom.data.mgt.ResponseObject;
import com.mts.toyskingdom.service.DiscountSv;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

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

    @PostMapping("/save")
    public ResponseObject<?> doPostSave(@RequestBody DiscountDTO discountDTO){
        var resultApi = new ResponseObject<>();
        try {
            int ketQua = sv.save(discountDTO);
            //Không có gì để lưu hoặc cập nhật
            if (ketQua == 0) {
                resultApi.setSuccess(false);
                resultApi.setMessage("Không có gì để cập nhật hoặc thêm");
            } else if(ketQua == 1) { // 1 khi lưu thành công
                resultApi.setSuccess(true);
                resultApi.setMessage("Thêm mới thành công");
            } else { // 2 khi cập nhật thành công
                resultApi.setSuccess(true);
                resultApi.setMessage("Cập nhật thành công");
            }
        } catch (Exception e) {
            resultApi.setSuccess(false);
            resultApi.setMessage("That bai khi xu ly du lieu");
            log.error("Fail When: ", e);
        }
        return resultApi;
    }

    @DeleteMapping("/disable")
    public ResponseObject<?> disable(@RequestParam int idDiscount) {
        var resultApi = new ResponseObject<>();
        try {
            if (sv.disable(idDiscount)) {
                resultApi.setSuccess(true);
                resultApi.setMessage("Đã xóa thành công");
            } else { //Id không tồn tại
                resultApi.setSuccess(false);
                resultApi.setMessage("Id không tồn tại");
            }
        } catch (SQLException e) {
            resultApi.setSuccess(false);
            resultApi.setMessage("Lỗi");
            log.error("Fail when: ", e.getMessage());
        }
        return resultApi;
    }

}
