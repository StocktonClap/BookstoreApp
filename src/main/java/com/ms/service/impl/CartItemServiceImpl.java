package com.ms.service.impl;

import com.ms.entities.*;
import com.ms.repository.BookToCartItemRepository;
import com.ms.repository.CartItemRepository;
import com.ms.service.CartITemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class CartItemServiceImpl implements CartITemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private BookToCartItemRepository bookToCartItemRepository;

    @Override
    public CartItem getCartItemById(int id) {
        return cartItemRepository.getById(id);
    }

    @Override
    public CartItem addBookToCartItem(Book book, User user, int quantity) {

        List<CartItem> cartItemList = getByShoppingCart(user.getShoppingCart());

        for (CartItem cartItem : cartItemList) {
            if (book.getId() == cartItem.getBook().getId()) {
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                cartItem.setSubTotal(BigDecimal.valueOf(book.getPrice()).multiply(new BigDecimal(quantity)));
                cartItemRepository.save(cartItem);
                return cartItem;
            }
        }

        CartItem cartItem = new CartItem();
        cartItem.setQuantity(quantity);
        cartItem.setSubTotal(BigDecimal.valueOf(book.getPrice()).multiply(new BigDecimal(quantity)));
        cartItem.setBook(book);

        cartItem = cartItemRepository.save(cartItem);

        BookToCartItem bookToCartItem = new BookToCartItem();
        bookToCartItem.setBook(book);
        bookToCartItem.setCartItem(cartItem);
        bookToCartItemRepository.save(bookToCartItem);

        return cartItem;
    }

    @Override
    public CartItem save(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    public List<CartItem> getByShoppingCart(ShoppingCart shoppingCart) {
        return cartItemRepository.getByShoppingCart(shoppingCart);
    }

    @Override
    public CartItem updateCartItem(CartItem cartItem) {
        BigDecimal updateCart = BigDecimal.valueOf(cartItem.getBook().getPrice()).multiply(new BigDecimal(cartItem.getQuantity()));
        updateCart = updateCart.setScale(2, RoundingMode.HALF_UP);
        cartItem.setSubTotal(updateCart);
        cartItemRepository.save(cartItem);
        return cartItem;
    }

    @Override
    public void removeCartItem(CartItem cartItem) {
        bookToCartItemRepository.deleteByCartItem(cartItem);
        cartItemRepository.delete(cartItem);
    }
}
