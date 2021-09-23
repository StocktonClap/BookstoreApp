package com.ms.entities;

import javax.persistence.*;

@Entity
public class BookToCartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "cart_item_id")
    private CartItem cartItem;

    public int getId() {
        return id;
    }

    public BookToCartItem setId(int id) {
        this.id = id;
        return this;
    }

    public Book getBook() {
        return book;
    }

    public BookToCartItem setBook(Book book) {
        this.book = book;
        return this;
    }

    public CartItem getCartItem() {
        return cartItem;
    }

    public BookToCartItem setCartItem(CartItem cartItem) {
        this.cartItem = cartItem;
        return this;
    }
}