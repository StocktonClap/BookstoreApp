package com.ms.controller;

import com.ms.entities.Role;
import com.ms.entities.User;
import com.ms.entities.UserRole;
import com.ms.exceptions.UserNotFoundException;
import com.ms.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @GetMapping(value = {"", "/"})
    public List<User> getUsers() {
        logger.info("Retrieving all users");
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") int id) throws UserNotFoundException {
        logger.info("Retrieving user by ID: " + id);
        return userService.getUserById(id);
    }

    @PostMapping("/newUser")
    public ResponseEntity<?> newUser(HttpServletRequest request, @RequestBody HashMap<String, String> mapper) {
        String username = mapper.get("username");
        String userEmail = mapper.get("email");

        if (userService.getUserByUsername(username) != null) {
            return new ResponseEntity<>("Username exists.", HttpStatus.BAD_REQUEST);
        }
        if (userService.getUserByEmail(userEmail) != null) {
            return new ResponseEntity<>("Email address exists.", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(userEmail);

        // TODO
        // password  - do zrobienia razem z Security !!!

        Role role = new Role();
        role.setRoleId(1);
        role.setName("ROLE_USER");

        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(new UserRole(user, role));
        userService.createUser(user, userRoles);

        // TODO
        // mail sender = wysyła maila przy zarejestrowaniu użytkownika,
        // można użyć token do wysłania i zarejestrowania czy enabled

        return new ResponseEntity<>("User added Succesfully.", HttpStatus.OK);
    }

    // TODO - updateUser implementation

    @PutMapping("/updateUser")
        public ResponseEntity<?> updateUser (@RequestBody HashMap<String, Object> mapper) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // TODO - Forgotten password implementation

    @PostMapping("/forgetPassword")
    public ResponseEntity<?> forgetPassword (HttpServletRequest request, @RequestBody HashMap<String, String> mapper) {
        return new ResponseEntity<>("Email sent!", HttpStatus.OK);
    }

}
