package com.mts.toyskingdom.data.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
    private int idOrderItem;
    private int idOrder;
    private int idProduct;
    private int orderQuantity;
    private double price;
}
