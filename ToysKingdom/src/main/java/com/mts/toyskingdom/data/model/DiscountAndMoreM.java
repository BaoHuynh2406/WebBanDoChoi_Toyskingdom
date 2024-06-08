package com.mts.toyskingdom.data.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.mts.toyskingdom.data.entity.DiscountAndMoreE;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiscountAndMoreM {
    private int idDiscount;
    private int idPrduct;
    private double discountPercent;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private Date startDay;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private Date endDay;
    private boolean active;
    private String productName;
    private String image;
    private double price;

    public static DiscountAndMoreM convertDiscountEtoDiscountM(DiscountAndMoreE discountAndMoreE) {
        return DiscountAndMoreM.builder()
                .idDiscount(discountAndMoreE.getIdDiscount())
                .idPrduct(discountAndMoreE.getIdPrduct())
                .discountPercent(discountAndMoreE.getDiscountPercent())
                .startDay(discountAndMoreE.getStartDay())
                .endDay(discountAndMoreE.getEndDay())
                .active(discountAndMoreE.isActive())
                .productName(discountAndMoreE.getProductName())
                .image(discountAndMoreE.getImage())
                .price(discountAndMoreE.getPrice())
                .build();
    }

    public static List<DiscountAndMoreM> convertListDiscountEtoListDiscountM(List<DiscountAndMoreE> discountEList) {
        return discountEList.stream().map(discountE -> convertDiscountEtoDiscountM(discountE)).collect(Collectors.toList());
    }
}
