package com.mts.toyskingdom.service;

import java.sql.SQLException;
import java.util.List;

import com.mts.toyskingdom.data.dto.ProductDTO;
import com.mts.toyskingdom.data.entity.ProductE;
import com.mts.toyskingdom.data.model.ProductFeatureM;
import com.mts.toyskingdom.data.model.ProductM;

public interface ProductSv {

    /**
     * Lấy tất cả thông tin: idProduct, idCategory, image, productName, quantity, price, description, unit, active
     *
     * @return Danh sách tất cả sản phẩm (bao gồm sản phẩm đã ẩn)
     */
    List<ProductM> getAllProducts() throws SQLException;

    /**
     * Lấy tất cả thông tin: sản phẩm đang mở
     *
     * @return Danh sách tất cả sản phẩm đang mở
     */
    List<ProductM> getAllProductsActive() throws SQLException;


    /**
     * @param idProduct Lấy tất cả thông tin: sản phẩm được chọn
     * @return Sản phẩm có ID
     */
    ProductFeatureM getProductByID(int idProduct) throws SQLException;


    List<ProductFeatureM> getAllProductsFeature() throws SQLException;

    //    tim sp theo ten
    List<ProductFeatureM> searchProduct(String productName) throws SQLException;

    //    Lấy số lượng của bảng product
    int countFeatureProducts() throws SQLException;

    //    Lấy sản phẩm từ start đến quantity
    List<ProductFeatureM> getProductFeaturePage(int start, int quantity) throws SQLException;


    // insert or update
    int saveProduct(ProductDTO productDTO) throws SQLException;

    // Delete
    boolean deleteProduct(int idProduct) throws SQLException;

    //disable
    boolean disableProduct(int idProduct) throws SQLException;


    // lấy sản phẩm để sắp xếp
    List<ProductM> getAllProductsSorted(String direction) throws SQLException;


}
