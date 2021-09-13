package com.ms.entities;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

public class Authority implements GrantedAuthority, Serializable {
    private static final long serialVersionUID = 123152L;

    private String authority;

    public Authority(String authority) {
        this.authority = authority;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public Authority setAuthority(String authority) {
        this.authority = authority;
        return this;
    }
}
