package com.ms.entities;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {

    private String authority;

    public Authority(String authority) {
        this.authority = authority;
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
