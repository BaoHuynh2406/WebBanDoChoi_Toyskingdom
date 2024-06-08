package com.mts.toyskingdom.mapper;

import com.mts.toyskingdom.data.entity.DiscountAndMoreE;
import com.mts.toyskingdom.data.model.DiscountAndMoreM;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DiscountMapper {
    List<DiscountAndMoreE> getAllDiscount();

    List<DiscountAndMoreE> getAllDiscountActive();

}
