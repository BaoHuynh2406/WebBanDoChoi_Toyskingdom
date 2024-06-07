package com.mts.toyskingdom.data.entity;

import lombok.Data;

import java.util.Date;

@Data
public class DiscountE {
    private int idDiscount;
    private int idPrduct;
    private double discountPercent;
    private Date startDay;
    private Date endDay;
    private boolean active;
}
