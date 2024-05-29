package com.mts.toyskingdom.service;

import com.mts.toyskingdom.data.model.CategoryM;

import java.sql.SQLException;
import java.util.List;

public interface CategorySv {

    List<CategoryM> getAllCategories() throws SQLException;

}
