package com.mts.toyskingdom.service.impl;

import com.mts.toyskingdom.data.dto.OrderDTO;
import com.mts.toyskingdom.data.model.OrderM;
import com.mts.toyskingdom.mapper.OrderMapper;
import com.mts.toyskingdom.service.OrderSv;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderSvImpl implements OrderSv {
    private final OrderMapper orderMapper;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public List<OrderM> getAll() {
        var listResultEntity = orderMapper.getAll();
        if (Objects.nonNull(listResultEntity)) {
            return OrderM.convertListOrderEtoOrderM(listResultEntity);
        }
        return null;
    }

    @Override
    public OrderM findById(int idOrder) throws SQLException {
        var listResultEntity = orderMapper.findById(idOrder);
        if (Objects.nonNull(listResultEntity)) {
            return OrderM.convertOrderEtoOrderM(listResultEntity);
        }
        return null;
    }

    @Override
    public OrderM findPendingByIdUser(int idUser) throws SQLException {
        var resultEntity = orderMapper.findPendingByIdUser(idUser);
        if (resultEntity != null) {
            return OrderM.convertOrderEtoOrderM(resultEntity);
        }
        return null;
    }

    @Override
    public boolean createOrder(OrderDTO orderDTO) throws SQLException {
        // Tạo đơn hàng mới khi khách hàng chưa có đơn hàng nào PENDING
        // Nếu khách hàng đã có đơn hàng đang PENDING thì không tạo
        if (orderMapper.findPendingByIdUser(orderDTO.getIdUser()) == null) {
            orderMapper.insert(orderDTO);
            return true;
        }
        return false;
    }

    @Override
    public int updateOrder(OrderDTO orderDTO) throws SQLException {
        return orderMapper.update(orderDTO);
    }

    @Override
    public Double getTotalRevenueBetweenDates(String startDate, String endDate) throws SQLException {
        try {
            DATE_FORMAT.setLenient(false);
            DATE_FORMAT.parse(startDate);
            DATE_FORMAT.parse(endDate);
        } catch (ParseException e) {
            throw new SQLException("Vui lòng nhập đúng định dạng dd-MM-yyyy.");
        }
        Map<String, Object> params = new HashMap<>();
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        return orderMapper.getTotalRevenueBetweenDates(params);
    }
//
//    @Override
//    public List<OrderM> getAllByStatus) {
//        var listResultEntity = orderMapper.getAllByStatus();
//        if (Objects.nonNull(listResultEntity)) {
//            return OrderM.convertListOrderEtoOrderM(listResultEntity);
//        }
//        return null;
//    }
}
