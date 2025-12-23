package com.example.demo.service.impl;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(RegisterRequest request) {
        // TEMP implementation for exam
        System.out.println("User registered: " + request.getEmail());
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        // TEMP implementation for exam
        return new AuthResponse("dummy-jwt-token", "ROLE_USER");
    }
}
