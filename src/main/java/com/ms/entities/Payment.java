package com.ms.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String type;
    private String cardName;
    private int cardNumber;
    private String expiryMonth;
    private String expiryYear;
    private int cvc;
    private String cardHolderName;
    private boolean defaultPayment;

    @OneToOne
    @JsonIgnore
    private Order order;

    public int getId() {
        return id;
    }

    public Payment setId(int id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public Payment setType(String type) {
        this.type = type;
        return this;
    }

    public String getCardName() {
        return cardName;
    }

    public Payment setCardName(String cardName) {
        this.cardName = cardName;
        return this;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public Payment setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public String getExpiryMonth() {
        return expiryMonth;
    }

    public Payment setExpiryMonth(String expiryMonth) {
        this.expiryMonth = expiryMonth;
        return this;
    }

    public String getExpiryYear() {
        return expiryYear;
    }

    public Payment setExpiryYear(String expiryYear) {
        this.expiryYear = expiryYear;
        return this;
    }

    public int getCvc() {
        return cvc;
    }

    public Payment setCvc(int cvc) {
        this.cvc = cvc;
        return this;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public Payment setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
        return this;
    }

    public boolean isDefaultPayment() {
        return defaultPayment;
    }

    public Payment setDefaultPayment(boolean defaultPayment) {
        this.defaultPayment = defaultPayment;
        return this;
    }

    public Order getOrder() {
        return order;
    }

    public Payment setOrder(Order order) {
        this.order = order;
        return this;
    }
}
