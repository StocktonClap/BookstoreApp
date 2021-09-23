package com.ms.controller;

import com.ms.entities.CartItem;
import com.ms.entities.Order;
import com.ms.entities.User;
import com.ms.service.CartItemService;
import com.ms.service.OrderService;
import com.ms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/orderList")
    public List<Order> getOrderList(User user) {
        User orderUser = userService.getUserByUsername(user.getUsername());
        List<Order> orderList = orderUser.getOrderList();

        return orderList;
    }

    @GetMapping("/cartItemList")
    public List<CartItem> getCartItemList(@RequestBody String orderId, User user) {
        Order order = orderService.getOrderById(Integer.parseInt(orderId));
        List<CartItem> cartItemList = cartItemService.getByOrder(order);

        return cartItemList;
    }

}
