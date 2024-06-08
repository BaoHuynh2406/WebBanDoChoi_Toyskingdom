package com.mts.toyskingdom.mapper;

import com.mts.toyskingdom.data.dto.DiscountDTO;
import com.mts.toyskingdom.data.entity.DiscountAndMoreE;
import com.mts.toyskingdom.data.entity.DiscountE;
import com.mts.toyskingdom.data.model.DiscountAndMoreM;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscountMapper {
    List<DiscountAndMoreE> getAllDiscount();

    List<DiscountAndMoreE> getAllDiscountActive();

    DiscountE findById(@Param("idDiscount") int idDiscount);

    int insert(DiscountDTO discountDTO);

    int update(DiscountDTO discountDTO);

    int delete(int idDiscount);

    int disable(@Param("idDiscount") int idDiscount);


}
