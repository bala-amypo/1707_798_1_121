package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.AuthRequest;
import com.example.demo.security.JwtUtil;

import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    @PostMapping("/login")
public AuthResponse login(@RequestBody AuthRequest request) {
    String email = userService.login(request);
    String token = jwtUtil.generateToken(email);
    return new AuthResponse(token);
}

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        String msg = userService.register(request);
        return new AuthResponse(msg);
    }
    @PostMapping("/login")
public AuthResponse login(@RequestBody AuthRequest request) {
    String email = userService.login(request);
    String token = jwtUtil.generateToken(email);
    return new AuthResponse(token);
}
}
