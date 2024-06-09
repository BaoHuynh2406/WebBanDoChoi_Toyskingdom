package com.mts.toyskingdom.mapper;

import com.mts.toyskingdom.data.dto.OrderItemDTO;
import com.mts.toyskingdom.data.entity.OrderItemE;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderItemMapper {

    List<OrderItemE> getAll();

    OrderItemE findItemInOrderDetail(OrderItemDTO orderItemDTO);

    OrderItemE findById(@Param("idOrderItem") int idOrderItem);

    int insert(OrderItemDTO orderItemDTO);

    int update(OrderItemDTO orderItemDTO);

    int delete(int idOrderItem);
}
