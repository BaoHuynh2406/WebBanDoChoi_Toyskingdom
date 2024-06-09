package com.mts.toyskingdom.data.model;


import lombok.Data;

@Data
public class CartItemM {
    private int idProduct;
    private int quantity;
    private String productName;
    private String image;
    private double price;
}
