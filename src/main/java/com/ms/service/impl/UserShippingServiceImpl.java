package com.ms.service.impl;

import com.ms.entities.UserShipping;
import com.ms.repository.UserShippingRepository;
import com.ms.service.UserShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserShippingServiceImpl implements UserShippingService {

    @Autowired
    UserShippingRepository userShippingRepository;

    @Override
    public UserShipping getUserShippingById(int id) {
        return userShippingRepository.getById(id);
    }

    @Override
    public void remove(int id) {
        userShippingRepository.deleteById(id);
    }
}
