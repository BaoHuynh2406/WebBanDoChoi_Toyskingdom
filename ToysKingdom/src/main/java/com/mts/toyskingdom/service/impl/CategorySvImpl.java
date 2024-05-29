package com.mts.toyskingdom.service.impl;

import com.mts.toyskingdom.data.model.CategoryM;
import com.mts.toyskingdom.mapper.CategoryMapper;
import com.mts.toyskingdom.service.CategorySv;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class CategorySvImpl implements CategorySv {

    final CategoryMapper categoryMapper;

    @Override
    public List<CategoryM> getAllCategories() throws SQLException {
        var listResultEntity = categoryMapper.getAllCategories();
        if (Objects.nonNull(listResultEntity)) {
            return CategoryM.convertListCategoryEtoCategoryM(listResultEntity);
        }
        return null;
    }
}