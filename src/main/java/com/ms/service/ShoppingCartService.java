package com.ms.service;

import com.ms.entities.ShoppingCart;

public interface ShoppingCartService {

    ShoppingCart update(ShoppingCart shoppingCart);
    void clear(ShoppingCart shoppingCart);
}
