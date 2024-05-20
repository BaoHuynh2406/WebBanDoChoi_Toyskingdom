package com.mts.ToysKingdom.mapper;

import com.mts.ToysKingdom.data.entity.ProductE;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface Product_Mapper {
//   Lấy một sản phẩm có id
    ProductE getProductByID(@Param("idProduct") int idProduct);
//    Lấy tất cả sản phẩm bao gồm đang active = false
    List<ProductE> getAllProducts();
//    Lấy tất cả sản phẩm có active = true
    List<ProductE> getAllActiveProducts();

}
