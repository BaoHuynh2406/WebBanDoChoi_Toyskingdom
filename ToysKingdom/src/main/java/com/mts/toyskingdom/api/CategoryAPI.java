package com.mts.toyskingdom.api;


import com.mts.toyskingdom.data.mgt.ResponseObject;
import com.mts.toyskingdom.service.CategorySv;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api-public/categories")
public class CategoryAPI {
    final CategorySv categoryService;

    @GetMapping("/getAllCategories")
    public ResponseObject<?> doGetAllCategory() {
        var resultApi = new ResponseObject<>();
        try {
            resultApi.setData(categoryService.getAllCategories());
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
