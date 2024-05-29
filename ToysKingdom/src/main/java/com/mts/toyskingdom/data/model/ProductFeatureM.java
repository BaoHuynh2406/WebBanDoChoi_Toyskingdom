package com.mts.toyskingdom.data.model;

import com.mts.toyskingdom.data.entity.ProductE;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductFeatureM {
    private int idProduct;
    private String idCategory;
    private String productName;
    private String des;
    private String image;
    private double price;
    private double discountPercent;


    public static ProductFeatureM convertProductEToProductM(ProductE productE) {
        return ProductFeatureM.builder()
                .idProduct(productE.getIdProduct())
                .idCategory(productE.getIdCategory())
                .productName(productE.getProductName())
                .des(productE.getDes())
                .image(productE.getImage())
                .price(productE.getPrice())
                .discountPercent(productE.getDiscountPercent())
                .build();
    }

    public static List<ProductFeatureM> convertListProductEToProductM(List<ProductE> productEList)
    {
        return productEList.stream().map(productE -> convertProductEToProductM(productE)).collect(Collectors.toList());
    }
}
