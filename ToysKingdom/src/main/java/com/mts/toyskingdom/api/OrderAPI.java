package com.mts.toyskingdom.api;


import com.mts.toyskingdom.data.mgt.ResponseObject;
import com.mts.toyskingdom.data.model.OrderM;
import com.mts.toyskingdom.service.OrderSv;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api-public/orders")
public class OrderAPI {
    final OrderSv sv;

    @GetMapping("/findPendingByIdUser")
    public ResponseObject<?> doGetFindPendingByIdUser(@RequestParam("idUser") int idUser) {
        var resultApi = new ResponseObject<>();
        try {
            OrderM data = sv.findPendingByIdUser(idUser);
            if (data == null) {
                resultApi.setSuccess(false);
                resultApi.setMessage("Không tìm thấy");
                return resultApi;
            }
            resultApi.setData(data);
            resultApi.setSuccess(true);
            resultApi.setMessage("Đã tìm thấy");
        } catch (Exception e) {
            resultApi.setSuccess(false);
            resultApi.setMessage("Lấy thông tin thất bại");
            log.error("Fail When: ", e);
        }
        return resultApi;
    }

    @GetMapping("/totalRevenueBetweenDates")
    public ResponseObject<?> getTotalRevenueBetweenDates(@RequestParam("startDate") String startDate,
                                                         @RequestParam("endDate") String endDate) {
        var resultApi = new ResponseObject<>();
        try {
            Double totalRevenue = sv.getTotalRevenueBetweenDates(startDate, endDate);
            resultApi.setData(totalRevenue);
            resultApi.setSuccess(true);
            resultApi.setMessage("Tổng doanh thu đã được lấy thành công");
        } catch (SQLException e) {
            resultApi.setSuccess(false);
            resultApi.setMessage(e.getMessage());
            log.error("Lỗi sau: ", e);
        } catch (Exception e) {
            resultApi.setSuccess(false);
            resultApi.setMessage("Lấy tổng doanh thu thất bại");
            log.error("Lỗi sau: ", e);
        }
        return resultApi;
    }
    @GetMapping("/getAllByStatus")
    public ResponseObject<?> getAllByStatus() {
        var resultApi = new ResponseObject<>();
        try {
            resultApi.setData(sv.getAllByStatus());
            resultApi.setSuccess(true);
            resultApi.setMessage("Danh sách đơn hàng đã được lấy thành công");
        } catch (Exception e) {
            resultApi.setSuccess(false);
            resultApi.setMessage("Lấy danh sách đơn hàng thất bại");
            log.error("Lỗi sau: ", e);
        }
        return resultApi;
    }
}
