package com.mts.toyskingdom.mapper;

import com.mts.toyskingdom.data.entity.ProductE;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
//   Lấy một sản phẩm có id
    ProductE getProductByID(@Param("idProduct") int idProduct);
//    Lấy tất cả sản phẩm bao gồm đang active = false
    List<ProductE> getAllProducts();
//    Lấy tất cả sản phẩm có active = true
    List<ProductE> getAllActiveProducts();
//    Lấy thông tin sản phẩm hữu ích. Tên, giá, ảnh, giảm giá..
    List<ProductE> getAllProductsFeature();

//    Tim sp theo ten
    List<ProductE> searchProduct(@Param("productName") String productName);

//    Lấy số lượng sản phẩm feature trong db
    int countFeatureProducts();

//    Lấy sản phẩm từ start
    List<ProductE> getProductsFrom(@Param("start") int start, @Param("quantity") int quantity);
}
