package com.mts.ToysKingdom.api;

import com.mts.ToysKingdom.data.mgt.ResponseObject;
import com.mts.ToysKingdom.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api-public/products")
public class ProductAPI {
    final ProductService productService;

    @GetMapping("/getAllProducts")
    public ResponseObject<?> doGetAllProducts() {
        var resultApi = new ResponseObject<>();
        try {
            resultApi.setData(productService.getAllProducts());
            resultApi.setSuccess(true);
            resultApi.setMessage("Lấy thông tin toàn bộ sản phẩm thành công");
        } catch (Exception e) {
            resultApi.setSuccess(false);
            resultApi.setMessage("Lấy thông tin sản phẩm thất bại");
            log.error("Fail When Call API /api-public/products/getAllProducts : ", e);
        }
        return resultApi;
    }

    @GetMapping("/getAllActiveProducts")
    public ResponseObject<?> doGetAllActiveProducts() {
        var resultApi = new ResponseObject<>();
        try {
            resultApi.setData(productService.getAllProductsActive());
            resultApi.setSuccess(true);
            resultApi.setMessage("Lấy thông tin toàn bộ sản phẩm active thành công");
        } catch (Exception e) {
            resultApi.setSuccess(false);
            resultApi.setMessage("Lấy thông tin sản phẩm thất bại");
            log.error("Fail When Call API /api-public/products/getAllActiveProducts : ", e);
        }
        return resultApi;
    }
}
