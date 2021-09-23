package com.ms.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class ShippingAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String receiverName;
    private String street;
    private String city;
    private String postCode;

    //  TODO
    //@OneToOne  // zrobić gety i sety po encji Order
    //@JsonIgnore
    //private Order order;

    public int getId() {
        return id;
    }

    public ShippingAddress setId(int id) {
        this.id = id;
        return this;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public ShippingAddress setReceiverName(String receiverName) {
        this.receiverName = receiverName;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public ShippingAddress setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getCity() {
        return city;
    }

    public ShippingAddress setCity(String city) {
        this.city = city;
        return this;
    }

    public String getPostCode() {
        return postCode;
    }

    public ShippingAddress setPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

}
