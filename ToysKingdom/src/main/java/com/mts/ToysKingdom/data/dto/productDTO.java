package com.mts.ToysKingdom.data.dto;

import lombok.Data;

@Data
public class productDTO {
    private int idProduct;
    private String productName;
    private String des;
    private String image;
    private double price;
    private String unit;
    private double quantity;
    private boolean active;

}
