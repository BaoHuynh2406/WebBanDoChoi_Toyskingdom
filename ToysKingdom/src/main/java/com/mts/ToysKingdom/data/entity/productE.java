package com.mts.ToysKingdom.data.entity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class productE {
    private int idProduct;
    private String productName;
    private String des;
    private String image;
    private double price;
    private String unit;
    private double quantity;
    private boolean active;
}
