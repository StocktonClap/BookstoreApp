package com.ms.service;

import com.ms.entities.User;
import com.ms.entities.UserRole;

import java.util.List;
import java.util.Set;

public interface UserService {

    User findById(int id);
    List<User> findAll();
    User findByUsername(String username);
    User findByEmail(String email);
    User save(User user);
    User createUser(User user, Set<UserRole> userRoles);
}
