package com.ms.controller;

import com.ms.entities.User;
import com.ms.entities.UserShipping;
import com.ms.service.UserService;
import com.ms.service.UserShippingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipping")
public class ShippingController {

    private static final Logger logger = LogManager.getLogger(ShippingController.class);

    @Autowired
    private UserShippingService userShippingService;

    @Autowired
    private UserService userService;

    @GetMapping({"", "/"})
    public List<UserShipping> getShippingList (User user) {
        User shippingUser = userService.getUserByUsername(user.getUsername());
        return shippingUser.getUserShippingList();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewUserShipping (@RequestBody UserShipping userShipping, User user) {
        User shippingUser = userService.getUserByUsername(user.getUsername());
        userService.updateShipping(userShipping, shippingUser);
        return new ResponseEntity<>("User shipping address added.", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeShipping (@RequestBody String id, User user) {
        userShippingService.remove(Integer.parseInt(id));
        return new ResponseEntity<>("Shipping deleted.", HttpStatus.OK);
    }

    @PostMapping("/setDefault")
    public ResponseEntity<?> setDefaultShipping (@RequestBody String id, User user) {
        User shippingUser = userService.getUserByUsername(user.getUsername());
        userService.setDefaultShipping(Integer.parseInt(id), shippingUser);
        return new ResponseEntity<>("Default address set.", HttpStatus.OK);
    }
}
