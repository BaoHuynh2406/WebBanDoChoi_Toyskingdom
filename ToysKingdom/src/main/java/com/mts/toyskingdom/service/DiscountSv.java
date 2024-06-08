package com.mts.toyskingdom.service;

import com.mts.toyskingdom.data.dto.DiscountDTO;
import com.mts.toyskingdom.data.model.DiscountAndMoreM;

import java.sql.SQLException;
import java.util.List;

public interface DiscountSv {
    List<DiscountAndMoreM> getAllDiscount() throws SQLException;

    List<DiscountAndMoreM> getAllDiscountActive() throws SQLException;

    int save(DiscountDTO discountDTO) throws SQLException;

    boolean disable(int idDiscount) throws SQLException;

    boolean delete(int idDiscount) throws SQLException;
}
