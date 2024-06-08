package com.mts.toyskingdom.service;

import com.mts.toyskingdom.data.model.DiscountAndMoreM;

import java.sql.SQLException;
import java.util.List;

public interface DiscountSv {
    List<DiscountAndMoreM> getAllDiscount() throws SQLException;

    List<DiscountAndMoreM> getAllDiscountActive() throws SQLException;
}
