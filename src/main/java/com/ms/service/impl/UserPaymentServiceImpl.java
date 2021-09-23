package com.ms.service.impl;

import com.ms.entities.UserPayment;
import com.ms.exceptions.UserPaymentNotFoundException;
import com.ms.repository.UserPaymentRepository;
import com.ms.service.UserPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserPaymentServiceImpl implements UserPaymentService {

    @Autowired
    UserPaymentRepository userPaymentRepository;

    @Override
    public UserPayment getById(int id) throws UserPaymentNotFoundException {
        Optional<UserPayment> result = userPaymentRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new UserPaymentNotFoundException("Could not find any shipping by ID " + id);
    }

    @Override
    public void remove(int id) {
    userPaymentRepository.deleteById(id);
    }
}
