package org.example.Livraisonderepas.security.controller;


import org.example.Livraisonderepas.security.jwt.JwtResponse;
import org.example.Livraisonderepas.security.model.User;
import org.example.Livraisonderepas.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Qualifier("adminService")
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public JwtResponse signIn(@RequestBody User user) {
        return userService.signIn(user);
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.findAll();
    }
}

