package com.mts.toyskingdom.mapper;

import com.mts.toyskingdom.data.dto.OrderDTO;
import com.mts.toyskingdom.data.entity.OrderE;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    List<OrderE> getAll();

    OrderE findById(int id);

    OrderE findPendingByIdUser(int idUser);

    int insert(OrderDTO orderDTO);

    int update(OrderDTO orderDTO);

}
