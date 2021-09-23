package com.ms.repository;

import com.ms.entities.CartItem;
import com.ms.entities.Order;
import com.ms.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    List<CartItem> getByShoppingCart(ShoppingCart shoppingCart);
    List<CartItem> getCartItemByOrder (Order order);
}
