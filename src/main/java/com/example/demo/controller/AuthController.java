package com.example.demo.controller;

import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.AuthRequest;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {
        return userService.register(
                request.getName(),
                request.getEmail(),
                request.getPassword()
        );
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request) {
        return userService.login(
                request.getEmail(),
                request.getPassword()
        );
    }
}
