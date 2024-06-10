package com.mts.toyskingdom.service;

import com.mts.toyskingdom.data.dto.CartItemDTO;
import com.mts.toyskingdom.data.model.CartItemM;

import java.sql.SQLException;
import java.util.List;

public interface CartSv {
    boolean addToCart(CartItemDTO cartItemDTO) throws SQLException;

    boolean removeFromCart(CartItemDTO cartItemDTO) throws SQLException;

    List<CartItemM> getCartByIdUser(int idUser) throws SQLException;
}
