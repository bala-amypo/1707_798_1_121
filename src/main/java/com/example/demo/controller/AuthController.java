package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest r) {
        userService.register(r);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest r) {
        return userService.login(r);
    }
}
