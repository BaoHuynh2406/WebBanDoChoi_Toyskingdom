package com.mts.ToysKingdom.data.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class productM {
    private int idProduct;
    private String productName;
    private String des;
    private String image;
    private double price;
    private String unit;
    private double quantity;
    private boolean active;


}
