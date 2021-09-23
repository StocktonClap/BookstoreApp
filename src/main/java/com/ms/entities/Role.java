package com.ms.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Role {

    @Id
    private int roleId;

    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserRole> userRoles = new HashSet<>();

    public Role() {
    }

    public int getRoleId() {
        return roleId;
    }

    public Role setRoleId(int roleId) {
        this.roleId = roleId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Role setName(String name) {
        this.name = name;
        return this;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public Role setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
        return this;
    }
}
