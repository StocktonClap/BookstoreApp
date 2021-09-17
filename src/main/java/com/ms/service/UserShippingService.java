package com.ms.service;

import com.ms.entities.UserShipping;
import com.ms.exceptions.UserShippingNotFoundException;

public interface UserShippingService {

    UserShipping getUserShippingById(int id) throws UserShippingNotFoundException;
    void remove(int id);
}
