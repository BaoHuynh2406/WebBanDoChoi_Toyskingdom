package com.mts.toyskingdom.data.dto;

import lombok.Data;

@Data
public class OrderDTO {
    private int idOrder;
    private int idUser;
    private String orderDate;
    private double total;
    private String orderStatus; // 'PENDING', 'PAID', 'CANCELLED'
}
