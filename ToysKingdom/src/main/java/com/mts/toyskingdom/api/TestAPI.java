package com.mts.toyskingdom.api;

import com.mts.toyskingdom.data.dto.TestDTO;
import com.mts.toyskingdom.data.mgt.ResponseObject;
import com.mts.toyskingdom.service.TestSv;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api-public/test")
public class TestAPI {

    final TestSv testSv;


    @GetMapping("/testParam")
    public ResponseObject<?> doTest(TestDTO testDTO) {
        ResponseObject resultApi = new ResponseObject<>();
        try {
            resultApi.setData(testSv.testService(testDTO));
            resultApi.setSuccess(true);
            resultApi.setMessage("Lấy thông tin toàn bộ sản phẩm thành công");
        } catch (Exception e) {
            resultApi.setSuccess(false);
            resultApi.setMessage("Lấy thông tin sản phẩm thất bại");
            log.error("Fail When Call API /api-public/products/getAllProducts : ", e);
        }
        return resultApi;
    }

    @PostMapping("/testPostParam")
    public ResponseObject<?> doPostTest(TestDTO testDTO) {
        ResponseObject resultApi = new ResponseObject<>();
        try {
            testSv.test1(testDTO);
            resultApi.setData("");
            resultApi.setSuccess(true);
            resultApi.setMessage("Lấy thông tin toàn bộ sản phẩm thành công");
        } catch (Exception e) {
            resultApi.setSuccess(false);
            resultApi.setMessage("Lấy thông tin sản phẩm thất bại");
            log.error("Fail When Call API /api-public/products/getAllProducts : ", e);
        }
        return resultApi;
    }
}
