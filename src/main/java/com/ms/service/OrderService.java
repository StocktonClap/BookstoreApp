package com.ms.service;

import com.ms.entities.*;

public interface OrderService {

    Order createOrder (ShoppingCart shoppingCart,
                       ShippingAddress shippingAddress,
                       BillingAddress billingAddress,
                       Payment payment,
                       User user);

    Order getOrderById (int id);
}
