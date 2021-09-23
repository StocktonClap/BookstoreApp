package com.ms.service.impl;

import com.ms.entities.CartItem;
import com.ms.entities.ShoppingCart;
import com.ms.repository.ShoppingCartRepository;
import com.ms.service.CartItemService;
import com.ms.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private CartItemService cartITemService;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        BigDecimal cartTotal = new BigDecimal(0);
        List<CartItem> cartItemList = cartITemService.getByShoppingCart(shoppingCart);

        for (CartItem cartItem : cartItemList) {
            if (cartItem.getBook().getStockNumber() > 0) {
                cartITemService.updateCartItem(cartItem);
                cartTotal = cartTotal.add(cartItem.getSubTotal());
            }
        }
        shoppingCart.setTotal(cartTotal);
        shoppingCartRepository.save(shoppingCart);
        return shoppingCart;
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
    List<CartItem> cartItemList = cartITemService.getByShoppingCart(shoppingCart);

    for (CartItem cartItem : cartItemList) {
        cartITemService.removeCartItem(cartItem);
    }

    shoppingCart.setTotal(new BigDecimal(0));
    shoppingCartRepository.save(shoppingCart);
    }
}
