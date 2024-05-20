package com.mts.ToysKingdom.mapper;
import com.mts.ToysKingdom.data.entity.productE;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface productMapper {
    productE getProduct(@Param("id_product") String id_product);
    List<productE> getAllProducts();

}
