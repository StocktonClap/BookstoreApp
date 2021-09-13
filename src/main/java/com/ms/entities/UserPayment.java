package com.ms.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class UserPayment implements Serializable {

    private static final long serialVersionUID = 680812L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type;
    private String cardName;
    private int cardNumber;
    private String expiryMonth;
    private String expiryYear;
    private int cvc;
    private String holderName;
    private boolean defaultPayment;

    @ManyToOne
    @JsonIgnore
    private User user;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "userPayment")
    private UserBilling userBilling;

    public int getId() {
        return id;
    }

    public UserPayment setId(int id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public UserPayment setType(String type) {
        this.type = type;
        return this;
    }

    public String getCardName() {
        return cardName;
    }

    public UserPayment setCardName(String cardName) {
        this.cardName = cardName;
        return this;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public UserPayment setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public String getExpiryMonth() {
        return expiryMonth;
    }

    public UserPayment setExpiryMonth(String expiryMonth) {
        this.expiryMonth = expiryMonth;
        return this;
    }

    public String getExpiryYear() {
        return expiryYear;
    }

    public UserPayment setExpiryYear(String expiryYear) {
        this.expiryYear = expiryYear;
        return this;
    }

    public int getCvc() {
        return cvc;
    }

    public UserPayment setCvc(int cvc) {
        this.cvc = cvc;
        return this;
    }

    public String getHolderName() {
        return holderName;
    }

    public UserPayment setHolderName(String holderName) {
        this.holderName = holderName;
        return this;
    }

    public boolean isDefaultPayment() {
        return defaultPayment;
    }

    public UserPayment setDefaultPayment(boolean defaultPayment) {
        this.defaultPayment = defaultPayment;
        return this;
    }

    public User getUser() {
        return user;
    }

    public UserPayment setUser(User user) {
        this.user = user;
        return this;
    }

    public UserBilling getUserBilling() {
        return userBilling;
    }

    public UserPayment setUserBilling(UserBilling userBilling) {
        this.userBilling = userBilling;
        return this;
    }
}
