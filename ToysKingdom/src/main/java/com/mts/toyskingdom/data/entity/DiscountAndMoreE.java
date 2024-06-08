package com.mts.toyskingdom.data.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class DiscountAndMoreE {
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

}
