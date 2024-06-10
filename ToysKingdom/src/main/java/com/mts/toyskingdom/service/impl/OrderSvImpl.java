package com.mts.toyskingdom.service.impl;

import com.mts.toyskingdom.data.dto.OrderDTO;
import com.mts.toyskingdom.data.model.CartItemM;
import com.mts.toyskingdom.data.model.OrderM;
import com.mts.toyskingdom.mapper.OrderMapper;
import com.mts.toyskingdom.service.OrderSv;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderSvImpl implements OrderSv {
    final OrderMapper mapper;

    @Override
    public List<OrderM> getAll() {
        var listResultEntity = mapper.getAll();
        if (Objects.nonNull(listResultEntity)) {
            return OrderM.convertListOrderEtoOrderM(listResultEntity);
        }
        return null;
    }


    @Override
    public OrderM findById(int idOrder) throws SQLException {
        var listResultEntity = mapper.findById(idOrder);
        if (Objects.nonNull(listResultEntity)) {
            return OrderM.convertOrderEtoOrderM(listResultEntity);
        }
        return null;
    }

    @Override
    public OrderM findPendingByIdUser(int idUser) throws SQLException {
        var resultEntity = mapper.findPendingByIdUser(idUser);
        if (resultEntity != null) {
            return OrderM.convertOrderEtoOrderM(resultEntity);
        }
        return null;
    }

    @Override
    public boolean createOrder(OrderDTO orderDTO) throws SQLException {
        //Tạo đơn hàng mới khi khách hàng chưa có đơn hàng nào PENDING
        //Nếu khách hàng đã có đơn hàng đang PENDING thì không tạo
        if (mapper.findPendingByIdUser(orderDTO.getIdUser()) == null) {
            mapper.insert(orderDTO);
            return true;
        }
        return false;

    }

    @Override
    public int updateOrder(OrderDTO orderDTO) throws SQLException {
        return mapper.update(orderDTO);
    }
}
