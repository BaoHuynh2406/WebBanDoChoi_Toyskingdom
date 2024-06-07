package com.mts.toyskingdom.data.model;

import com.mts.toyskingdom.data.entity.CategoryE;
import com.mts.toyskingdom.data.entity.OrderE;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderM {
    private int idOrder;
    private int idUser;
    private String orderDate;
    private double total;
    private String orderStatus;

    public static OrderM convertOrderEtoOrderM(OrderE orderE) {
        return OrderM.builder()
                .idOrder(orderE.getIdOrder())
                .idUser(orderE.getIdUser())
                .orderDate(orderE.getOrderDate())
                .total(orderE.getTotal())
                .orderStatus(orderE.getOrderStatus())
                .build();
    }

    public static List<OrderM> convertListOrderEtoOrderM(List<OrderE> orderEList) {
        return orderEList.stream().map(orderE -> convertOrderEtoOrderM(orderE)).collect(Collectors.toList());
    }
}
