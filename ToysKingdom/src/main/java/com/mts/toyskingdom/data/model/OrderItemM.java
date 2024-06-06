package com.mts.toyskingdom.data.model;

import com.mts.toyskingdom.data.entity.OrderE;
import com.mts.toyskingdom.data.entity.OrderItemE;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemM {
    private int idOrderItem;
    private int idOrder;
    private int idProduct;
    private int orderQuantity;
    private double price;
    private String unit;

    public static OrderItemM convertOrderItemEtoOrderItemM(OrderItemE orderItemE) {
        return OrderItemM.builder()
                .idOrderItem(orderItemE.getIdOrderItem())
                .idOrder(orderItemE.getIdOrder())
                .idProduct(orderItemE.getIdProduct())
                .orderQuantity(orderItemE.getOrderQuantity())
                .price(orderItemE.getPrice())
                .unit(orderItemE.getUnit())
                .build();
    }

    public static List<OrderItemM> convertListOrderItemEtoListOrderItemM(List<OrderItemE> orderItemEList) {
        return orderItemEList.stream().map(orderItemE -> convertOrderItemEtoOrderItemM(orderItemE)).collect(Collectors.toList());
    }
}
