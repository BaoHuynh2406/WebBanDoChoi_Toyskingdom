package com.mts.toyskingdom.data.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mts.toyskingdom.data.entity.DiscountE;
import com.mts.toyskingdom.data.entity.OrderE;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiscountM {
    private int idDiscount;
    private int idPrduct;
    private double discountPercent;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private Date startDay;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private Date endDay;
    private boolean active;

    public static DiscountM convertDiscountEtoDiscountM(DiscountE discountE) {
        return DiscountM.builder()
                .idDiscount(discountE.getIdDiscount())
                .idPrduct(discountE.getIdPrduct())
                .discountPercent(discountE.getDiscountPercent())
                .startDay(discountE.getStartDay())
                .endDay(discountE.getEndDay())
                .active(discountE.isActive())
                .build();
    }

    public static List<DiscountM> convertListDiscountEtoListDiscountM(List<DiscountE> discountEList) {
        return discountEList.stream().map(discountE -> convertDiscountEtoDiscountM(discountE)).collect(Collectors.toList());
    }
}
