package com.ms.controller;

import com.ms.entities.User;
import com.ms.entities.UserBilling;
import com.ms.entities.UserPayment;
import com.ms.service.UserPaymentService;
import com.ms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private UserPaymentService userPaymentService;

    @Autowired
    private UserService userService;

    @GetMapping("/userPaymentList")
    public List<UserPayment> getUserPaymentList(User user) {
        User payUser = userService.getUserByUsername(user.getUsername());
        List<UserPayment> userPaymentList = payUser.getUserPaymentList();
        return userPaymentList;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewUserPayment(@RequestBody UserPayment userPayment, User user) {
        User payUser = userService.getUserByUsername(user.getUsername());
        UserBilling userBilling = userPayment.getUserBilling();
        userService.updatePayment(userPayment, userBilling, payUser);
        return new ResponseEntity<>("Payment added.", HttpStatus.OK);
    }

    @DeleteMapping ("/remove")
    public ResponseEntity<?> removeUserPayment(@RequestBody String id, User user) {
        userPaymentService.remove(Integer.parseInt(id));
        return new ResponseEntity<>("User Payment deleted successfully.", HttpStatus.OK);
    }

    @PostMapping("/setDefault")
    public ResponseEntity<?> setDefaultPayment(@RequestBody String userPaymentId, User user) {
        User payUser = userService.getUserByUsername(user.getUsername());
        userService.setDefaultPayment(Integer.parseInt(userPaymentId), payUser);
        return new ResponseEntity<>("Default payment added to User.", HttpStatus.OK);
    }
}
