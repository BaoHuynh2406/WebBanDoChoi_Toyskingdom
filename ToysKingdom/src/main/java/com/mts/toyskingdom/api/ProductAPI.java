package com.mts.toyskingdom.api;

import com.mts.toyskingdom.data.dto.ProductDTO;
import com.mts.toyskingdom.data.mgt.ResponseObject;
import com.mts.toyskingdom.service.ProductSv;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api-public/products")
public class ProductAPI {

    @Value("${file.upload-dir}")
    private String uploadDir;
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
            int pageNumber = (page-1)*12;
            resultApi.setData(productService.getProductFeaturePage(pageNumber, 12));
            resultApi.setSuccess(true);
            resultApi.setMessage(page+"");
        } catch (Exception e) {
            resultApi.setSuccess(false);
            resultApi.setMessage("Lấy trang thất bại");
            log.error("Fail When Call API : ", e);
        }
        return resultApi;
    }



    @PostMapping("/save")
    public ResponseObject<?> doPostSaveProduct(@RequestBody ProductDTO productDTO){
        var resultApi = new ResponseObject<>();
        try {
            int ketQua = productService.saveProduct(productDTO);
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
    public ResponseObject<?> disable(@RequestParam int idProduct) {
        var resultApi = new ResponseObject<>();
        try {
            if (productService.disableProduct(idProduct)) {
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


    @PostMapping("/uploadImage")
    public ResponseObject<?> uploadImage(@RequestParam("file") MultipartFile file) {
        var resultApi = new ResponseObject<>();
        try {
            if (file.isEmpty()) {
                resultApi.setSuccess(false);
                resultApi.setMessage("File is empty");
                return resultApi;
            }

            // Convert relative path to absolute path
            Path currentPath = Paths.get("").toAbsolutePath();
            Path absolutePath = Paths.get(currentPath.toString(), uploadDir);

            if (!Files.exists(absolutePath)) {
                Files.createDirectories(absolutePath);
            }

            String filePath = absolutePath.resolve(file.getOriginalFilename()).toString();
            file.transferTo(new File(filePath));

            resultApi.setSuccess(true);
            resultApi.setMessage("File uploaded successfully: " + file.getOriginalFilename());
        } catch (IOException e) {
            resultApi.setSuccess(false);
            resultApi.setMessage("File upload failed");
            log.error("Fail When Call API /api-public/products/uploadImage : ", e);
        }
        return resultApi;
    }

}
