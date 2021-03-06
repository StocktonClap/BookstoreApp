package com.ms.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class UserShipping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String receiverName;
    private String street;
    private String city;
    private String postCode;
    private Boolean defaultShipping;

    @ManyToOne
    @JsonIgnore
    private User user;

    public int getId() {
        return id;
    }

    public UserShipping setId(int id) {
        this.id = id;
        return this;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public UserShipping setReceiverName(String receiverName) {
        this.receiverName = receiverName;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public UserShipping setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getCity() {
        return city;
    }

    public UserShipping setCity(String city) {
        this.city = city;
        return this;
    }

    public Boolean getDefaultShipping() {
        return defaultShipping;
    }

    public UserShipping setDefaultShipping(Boolean defaultShipping) {
        this.defaultShipping = defaultShipping;
        return this;
    }

    public User getUser() {
        return user;
    }

    public UserShipping setUser(User user) {
        this.user = user;
        return this;
    }

    public String getPostCode() {
        return postCode;
    }

    public UserShipping setPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }
}
