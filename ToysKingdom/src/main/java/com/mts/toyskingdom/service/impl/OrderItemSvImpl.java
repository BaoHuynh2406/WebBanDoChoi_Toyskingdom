package com.mts.toyskingdom.service.impl;

import com.mts.toyskingdom.data.dto.OrderItemDTO;
import com.mts.toyskingdom.data.model.OrderItemM;
import com.mts.toyskingdom.data.model.OrderM;
import com.mts.toyskingdom.mapper.OrderItemMapper;
import com.mts.toyskingdom.service.OrderItemSv;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderItemSvImpl implements OrderItemSv {
    final OrderItemMapper mapper;

    @Override
    public List<OrderItemM> getAll() throws SQLException {
        var listResultEntity = mapper.getAll();
        if (Objects.nonNull(listResultEntity)) {
            return OrderItemM.convertListOrderItemEtoListOrderItemM(listResultEntity);
        }
        return null;
    }

    @Override
    public OrderItemM findItemInOrderDetail(OrderItemDTO orderItemDTO) throws SQLException {
        var listResultEntity = mapper.findItemInOrderDetail(orderItemDTO);
        if (Objects.nonNull(listResultEntity)) {
            return OrderItemM.convertOrderItemEtoOrderItemM(listResultEntity);
        }
        return null;
    }

    @Override
    public OrderItemM findById(int idOrderItem) throws SQLException {
        return OrderItemM.convertOrderItemEtoOrderItemM(mapper.findById(idOrderItem));
    }


    //Nhận vào mã đơn hàng, mã sản phẩm, số lượng
    //Kiểm tra xem sản phẩm đó có trong giỏ hàng không
    //Nếu có thì tăng số lượng
    //Nếu không có thì thêm mới sản phẩm vào đơn hàng chi tiết
    @Override
    public void addOrderDetails(OrderItemDTO orderItemDTO) throws SQLException {
        //Kiễm tra sản phẩm có trong giỏ hàng không
        if (mapper.findItemInOrderDetail(orderItemDTO) != null) {
            System.out.println(orderItemDTO);
            //Nếu có thì cập nhật lại số lượng
            mapper.update(orderItemDTO);
            mapper.delete_order_items_with_zero_quantity(orderItemDTO.getIdOrder());
            return;
        }
        //Nếu không có thì thêm mới sản phẩm vào đơn hàng chi tiết
        mapper.insert(orderItemDTO);
        mapper.delete_order_items_with_zero_quantity(orderItemDTO.getIdOrder());
    }

    @Override
    public int insert(OrderItemDTO orderItemDTO) throws SQLException {
        return mapper.insert(orderItemDTO);
    }

    @Override
    public int update(OrderItemDTO orderItemDTO) throws SQLException {
        return mapper.update(orderItemDTO);
    }

    @Override
    public int delete(int idOrderItem) throws SQLException {
        return mapper.delete(idOrderItem);
    }
}
