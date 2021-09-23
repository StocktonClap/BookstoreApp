package com.ms.entities;

import javax.persistence.*;

@Entity
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userRoleId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

    public UserRole(User user, Role role) {
    }

    public UserRole() {
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public UserRole setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
        return this;
    }

    public User getUser() {
        return user;
    }

    public UserRole setUser(User user) {
        this.user = user;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public UserRole setRole(Role role) {
        this.role = role;
        return this;
    }
}


