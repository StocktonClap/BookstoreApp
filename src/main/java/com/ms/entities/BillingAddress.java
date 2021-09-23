package com.ms.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class BillingAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String billingAddressName;
    private String billingAddressStreet;
    private String billingAddressCity;


    @OneToOne
    @JsonIgnore
    private Order order;

    public int getId() {
        return id;
    }

    public BillingAddress setId(int id) {
        this.id = id;
        return this;
    }

    public String getBillingAddressName() {
        return billingAddressName;
    }

    public BillingAddress setBillingAddressName(String billingAddressName) {
        this.billingAddressName = billingAddressName;
        return this;
    }

    public String getBillingAddressStreet() {
        return billingAddressStreet;
    }

    public BillingAddress setBillingAddressStreet(String billingAddressStreet) {
        this.billingAddressStreet = billingAddressStreet;
        return this;
    }

    public String getBillingAddressCity() {
        return billingAddressCity;
    }

    public BillingAddress setBillingAddressCity(String billingAddressCity) {
        this.billingAddressCity = billingAddressCity;
        return this;
    }

    public Order getOrder() {
        return order;
    }

    public BillingAddress setOrder(Order order) {
        this.order = order;
        return this;
    }
}
