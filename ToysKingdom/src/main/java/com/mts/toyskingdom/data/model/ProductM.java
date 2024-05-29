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
public class ProductM {
    private int idProduct;
    private String idCategory;
    private String productName;
    private String des;
    private String image;
    private double price;
    private String unit;
    private double quantity;
    private boolean active;

    public static ProductM convertProductEToProductM(ProductE productE) {
        return ProductM.builder()
                .idProduct(productE.getIdProduct())
                .idCategory(productE.getIdCategory())
                .productName(productE.getProductName())
                .des(productE.getDes())
                .image(productE.getImage())
                .price(productE.getPrice())
                .unit(productE.getUnit())
                .quantity(productE.getQuantity())
                .active(productE.isActive())
                .build();
    }

    public static List<ProductM> convertListProductEToProductM(List<ProductE> productEList)
    {
        return productEList.stream().map(productE -> convertProductEToProductM(productE)).collect(Collectors.toList());
    }
}
