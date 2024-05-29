package com.mts.toyskingdom.mapper;

import com.mts.toyskingdom.data.entity.CategoryE;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<CategoryE> getAllCategories();
}
