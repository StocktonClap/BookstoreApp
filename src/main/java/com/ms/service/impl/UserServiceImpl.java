package com.ms.service.impl;

import com.ms.entities.*;
import com.ms.exceptions.UserNotFoundException;
import com.ms.repository.*;
import com.ms.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserShippingRepository userShippingRepository;

    @Autowired
    private UserBillingRepository userBillingRepository;

    @Autowired
    private UserPaymentRepository userPaymentRepository;

    @Override
    public User getUserById(int id) throws UserNotFoundException {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found."));
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User createUser(User user, Set<UserRole> userRoles) {
        User newUser = userRepository.getUserByUsername(user.getUsername());
        if (newUser != null) {
            logger.info("User with that username already exist.", user.getUsername());
        } else {
            for (UserRole userRole : userRoles) {
                roleRepository.save(userRole.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            user.setUserShippingList(new ArrayList<UserShipping>());
            user.setUserPaymentList(new ArrayList<UserPayment>());

            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setUser(user);
            user.setShoppingCart(shoppingCart);

            newUser = userRepository.save(user);
        }
        return newUser;
    }

    @Override
    public void remove(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateShipping(UserShipping userShipping, User user) {
        userShipping.setUser(user);
        userShipping.setDefaultShipping(true);
        user.getUserShippingList().add(userShipping);
        userRepository.save(user);
    }

    @Override
    public void setDefaultShipping(int shippingId, User user) {
        List<UserShipping> userShippingList = userShippingRepository.findAll(); // (List<UserShipping>)

        for (UserShipping userShipping : userShippingList) {
            if (userShipping.getId() == shippingId) {
                userShipping.setDefaultShipping(true);
                userShippingRepository.save(userShipping);
            } else {
                userShipping.setDefaultShipping(false);
                userShippingRepository.save(userShipping);
            }
        }
    }

    @Override
    public void updatePayment(UserPayment userPayment, UserBilling userBilling, User user) {
        userPayment.setUser(user);
        userPayment.setUserBilling(userBilling);
        userPayment.setDefaultPayment(true);
        System.out.println("UserBilling: " +userBilling);
        System.out.println(userPayment);
        userBilling.setUserPayment(userPayment);
        user.getUserPaymentList().add(userPayment);
        userPaymentRepository.save(userPayment);
        userBillingRepository.save(userBilling);
        userRepository.save(user);
    }

    @Override
    public void setDefaultPayment(int paymentId, User user) {
        List<UserPayment> userPaymentList = userPaymentRepository.findAll(); // (List<UserPayment>)

        for (UserPayment userPayment : userPaymentList) {
            if (userPayment.getId() == paymentId) {
                userPayment.setDefaultPayment(true);
                userPaymentRepository.save(userPayment);
            } else {
                userPayment.setDefaultPayment(false);
                userPaymentRepository.save(userPayment);
            }
        }
    }
}
