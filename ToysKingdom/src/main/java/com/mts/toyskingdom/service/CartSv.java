package com.mts.toyskingdom.service;

import java.sql.SQLException;

public interface CartSv {
    boolean addToCart() throws SQLException;
    boolean removeFromCart() throws SQLException;

}
