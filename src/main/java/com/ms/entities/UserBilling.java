package com.ms.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class UserBilling implements Serializable {

    private static final long serialVersionUID = 858721L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userBillingName;
    private String userBillingStreet;
    private String userBillingCity;

    @OneToOne
    @JsonIgnore
    private UserPayment userPayment;

    public int getId() {
        return id;
    }

    public UserBilling setId(int id) {
        this.id = id;
        return this;
    }

    public String getUserBillingName() {
        return userBillingName;
    }

    public UserBilling setUserBillingName(String userBillingName) {
        this.userBillingName = userBillingName;
        return this;
    }

    public String getUserBillingStreet() {
        return userBillingStreet;
    }

    public UserBilling setUserBillingStreet(String userBillingStreet) {
        this.userBillingStreet = userBillingStreet;
        return this;
    }

    public String getUserBillingCity() {
        return userBillingCity;
    }

    public UserBilling setUserBillingCity(String userBillingCity) {
        this.userBillingCity = userBillingCity;
        return this;
    }

    public UserPayment getUserPayment() {
        return userPayment;
    }

    public UserBilling setUserPayment(UserPayment userPayment) {
        this.userPayment = userPayment;
        return this;
    }
}
