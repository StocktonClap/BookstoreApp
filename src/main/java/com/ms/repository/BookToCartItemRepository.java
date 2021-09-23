package com.ms.repository;

import com.ms.entities.BookToCartItem;
import com.ms.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookToCartItemRepository extends JpaRepository <BookToCartItem, Integer>{

    void deleteByCartItem(CartItem cartItem);
}
