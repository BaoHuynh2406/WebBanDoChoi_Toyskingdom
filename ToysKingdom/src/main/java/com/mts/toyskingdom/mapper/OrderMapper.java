package com.mts.toyskingdom.mapper;

import com.mts.toyskingdom.data.dto.OrderDTO;
import com.mts.toyskingdom.data.entity.OrderE;
import com.mts.toyskingdom.data.model.CartItemM;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {
    List<OrderE> getAll();

    List<CartItemM> getOrderItemByIdOrder(@Param("idOrder") int idOrder);

    OrderE findById(int id);

    OrderE findPendingByIdUser(@Param("idUser") int idUser);

    int insert(OrderDTO orderDTO);

    int update(OrderDTO orderDTO);


    Double getTotalRevenueBetweenDates(Map<String, Object> params);
}
