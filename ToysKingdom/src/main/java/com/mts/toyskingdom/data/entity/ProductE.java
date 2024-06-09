package com.mts.toyskingdom.data.entity;



//import lombok.*;
//
//@Data
//
//public class ProductE {
//    private int idProduct;
//    private String idCategory;
//    private String productName;
//    private String des;
//    private String image;
//    private double price;
//    private String unit;
//    private double quantity;
//    private boolean active;
//    private double discountPercent;
//}

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Entity
public class ProductE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProduct;
    private String idCategory;
    private String productName;
    private String des;
    private String image;
    private double price;
    private String unit;
    private double quantity;
    private boolean active;
    private double discountPercent;
}
