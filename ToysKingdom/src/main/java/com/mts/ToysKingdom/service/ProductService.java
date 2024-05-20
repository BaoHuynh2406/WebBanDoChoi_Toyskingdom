package com.mts.ToysKingdom.service;

import java.sql.SQLException;
import java.util.List;
import com.mts.ToysKingdom.data.model.ProductM;

public interface ProductService {

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
     * @param idProduct
     * Lấy tất cả thông tin: sản phẩm được chọn
     *
     * @return Sản phẩm có ID
     */
    ProductM getProductByID(int idProduct) throws SQLException;


}
