package com.mts.toyskingdom.data.dto;

import lombok.Data;

@Data
public class ProductDTO {
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
