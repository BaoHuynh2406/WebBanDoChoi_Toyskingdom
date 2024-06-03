package com.mts.toyskingdom.data.entity;

import lombok.Data;

@Data
public class OrderE {
    private int idOrder;
    private int idUser;
    private String orderDate;
    private double total;
    private String orderStatus;

}
