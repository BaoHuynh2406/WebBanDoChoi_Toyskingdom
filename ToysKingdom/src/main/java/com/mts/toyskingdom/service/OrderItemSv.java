package com.mts.toyskingdom.service;

import com.mts.toyskingdom.data.dto.OrderItemDTO;
import com.mts.toyskingdom.data.model.OrderItemM;

import java.sql.SQLException;
import java.util.List;

public interface OrderItemSv {
    List<OrderItemM> getAll() throws SQLException;

    OrderItemM findItemInOrderDetail(OrderItemDTO orderItemDTO) throws SQLException;

    OrderItemM findById(int idOrderItem) throws SQLException;

    void addOrderDetails(OrderItemDTO orderItemDTO) throws SQLException;

    int insert(OrderItemDTO orderItemDTO) throws SQLException;

    int update(OrderItemDTO orderItemDTO) throws SQLException;

    int delete(int idOrderItem) throws SQLException;
}
