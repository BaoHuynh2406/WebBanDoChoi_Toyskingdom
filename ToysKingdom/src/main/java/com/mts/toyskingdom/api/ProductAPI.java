package com.mts.toyskingdom.api;

import com.mts.toyskingdom.data.mgt.ResponseObject;
import com.mts.toyskingdom.service.ProductSv;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api-public/products")
public class ProductAPI {
    final ProductSv productService;

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

    @GetMapping("/getAllProductsFeature")
    public ResponseObject<?> doGetAllProductsFeature() {
        var resultApi = new ResponseObject<>();
        try {
            resultApi.setData(productService.getAllProductsFeature());
            resultApi.setSuccess(true);
            resultApi.setMessage("Lấy thông tin toàn bộ sản phẩm feature thành công");
        } catch (Exception e) {
            resultApi.setSuccess(false);
            resultApi.setMessage("Lấy thông tin sản phẩm thất bại");
            log.error("Fail When Call API /api-public/products/getAllProductsFeature : ", e);
        }
        return resultApi;
    }

    // tim sp theo ten api
    @GetMapping("/getproductbyname")
    public ResponseObject<?> searchProductbyName(@RequestParam("productName") String productName) {
        var resultApi = new ResponseObject<>();
        try {
            resultApi.setData(productService.searchProduct(productName));
            resultApi.setSuccess(true);
            resultApi.setMessage("Tim thông tin sản phẩm thành công");
        } catch (Exception e) {
            resultApi.setSuccess(false);
            resultApi.setMessage("Tim thông tin sản phẩm thất bại");
            log.error("Fail When Call API /api-public/products/getAllProductsFeature : ", e);
        }
        return resultApi;
    }

//    Lấy số lướng sản phẩm features trong db
    @GetMapping("/countFeatureProducts")
    public ResponseObject<?> getCountFeatureProducts(){
        var resultApi = new ResponseObject<>();
        try {
            resultApi.setData(productService.countFeatureProducts());
            resultApi.setSuccess(true);
            resultApi.setMessage("Lấy số lượng sản phẩm feature");
        } catch (Exception e) {
            resultApi.setSuccess(false);
            resultApi.setMessage("Lấy số lượng sản phẩm thất bại");
            log.error("Fail When Call API : ", e);
        }
        return resultApi;
    }

//    Trả dữ liệu về trang home page
    @GetMapping("/homePageProduct")
    public ResponseObject<?> getHomePageProduct(@RequestParam("page") int page){
        var resultApi = new ResponseObject<>();
        try {
            resultApi.setData(productService.getProductFeaturePage(page));
            resultApi.setSuccess(true);
            resultApi.setMessage("Lấy số lượng sản phẩm feature");
        } catch (Exception e) {
            resultApi.setSuccess(false);
            resultApi.setMessage("Lấy số lượng sản phẩm thất bại");
            log.error("Fail When Call API : ", e);
        }
        return resultApi;
    }
}
