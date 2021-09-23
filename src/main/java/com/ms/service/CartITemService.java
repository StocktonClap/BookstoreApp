package com.ms.service;

import com.ms.entities.Book;
import com.ms.entities.CartItem;
import com.ms.entities.ShoppingCart;
import com.ms.entities.User;

import java.util.List;

public interface CartITemService {

    CartItem getCartItemById(int id);
    CartItem addBookToCartItem(Book book, User user, int quantity);
    CartItem save(CartItem cartItem);
    List<CartItem> getByShoppingCart(ShoppingCart shoppingCart);
   // List<CartItem> getByOrder(Order order);
    CartItem updateCartItem(CartItem cartItem);
    void removeCartItem(CartItem cartItem);

}
