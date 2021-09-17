package com.ms.service;

import com.ms.entities.UserPayment;

import com.ms.exceptions.UserPaymentNotFoundException;

public interface UserPaymentService {

    UserPayment getById(int id) throws UserPaymentNotFoundException;
    void deleteById(int id);
}
