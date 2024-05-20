package com.mts.ToysKingdom.data.entity;


import lombok.*;

@Data
public class ProductE {
    private int idProduct;
    private String idCategory;
    private String productName;
    private String des;
    private String image;
    private double price;
    private String unit;
    private double quantity;
    private boolean active;
}
