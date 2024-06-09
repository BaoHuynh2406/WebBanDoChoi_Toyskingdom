package com.mts.toyskingdom.data.dto;

import lombok.Data;

@Data
public class CartItemDTO {
    private int idUser;
    private int idProduct;
    private int quantity;
}
