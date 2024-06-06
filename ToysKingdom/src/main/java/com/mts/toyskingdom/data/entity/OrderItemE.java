package com.mts.toyskingdom.data.entity;

import lombok.Data;

@Data
public class OrderItemE {
    private int idOrderItem;
    private int idOrder;
    private int idProduct;
    private int orderQuantity;
    private double price;
    private String unit;
}
