package com.ms.service;

import com.ms.entities.*;
import com.ms.exceptions.UserNotFoundException;

import java.util.List;
import java.util.Set;

public interface UserService {

    User getUserById(int id) throws UserNotFoundException;

    List<User> getAll();

    User getUserByUsername(String username);

    User getUserByEmail(String email);

    User save(User user);

    User createUser(User user, Set<UserRole> userRoles);

    void remove (int id);

    void updateShipping(UserShipping userShipping, User user);

    void setDefaultShipping(int shippingId, User user);

    void updatePayment(UserPayment userPayment, UserBilling userBilling, User user);

    void setDefaultPayment(int paymentId, User user);
}


