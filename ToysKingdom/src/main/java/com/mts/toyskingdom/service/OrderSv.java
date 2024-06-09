package com.mts.toyskingdom.service;


import com.mts.toyskingdom.data.dto.OrderDTO;
import com.mts.toyskingdom.data.model.CartItemM;
import com.mts.toyskingdom.data.model.OrderM;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.sql.SQLException;
import java.util.List;

public interface OrderSv {
    List<OrderM> getAll();

    OrderM findById(@Param("idOrder") int idOrder) throws SQLException;

    OrderM findPendingByIdUser(@Param("idUser") int idUser) throws SQLException;

    boolean createOrder(OrderDTO orderDTO) throws SQLException;

    int updateOrder(OrderDTO orderDTO) throws SQLException;
}
