package com.ms.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private BigDecimal total;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<CartItem> cartItems;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private User user;

    public int getId() {
        return id;
    }

    public ShoppingCart setId(int id) {
        this.id = id;
        return this;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public ShoppingCart setTotal(BigDecimal total) {
        this.total = total;
        return this;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public ShoppingCart setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
        return this;
    }

    public User getUser() {
        return user;
    }

    public ShoppingCart setUser(User user) {
        this.user = user;
        return this;
    }
}
