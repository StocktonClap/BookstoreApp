package com.ms.repository;

import com.ms.entities.BillingAddress;
import com.ms.entities.Order;
import com.ms.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> getOrderByUser(User user);
    List<Order> getOrderByBillingAddress(BillingAddress billingAddress);
}
