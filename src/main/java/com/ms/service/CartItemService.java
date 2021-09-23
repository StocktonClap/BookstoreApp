package com.ms.service;

import com.ms.entities.*;

import java.util.List;

public interface CartItemService {

    CartItem getCartItemById(int id);
    void addBookToCartItem(Book book, User user, int quantity);
    CartItem save(CartItem cartItem);
    List<CartItem> getByShoppingCart(ShoppingCart shoppingCart);
    List<CartItem> getByOrder(Order order);
    void updateCartItem(CartItem cartItem);
    void removeCartItem(CartItem cartItem);

}
