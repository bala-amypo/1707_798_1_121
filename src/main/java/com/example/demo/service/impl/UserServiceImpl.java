package com.example.demo.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void register(RegisterRequest request) {
        passwordEncoder.encode(request.getPassword());
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        String token = jwtUtil.generateToken(request.getEmail());
        return new AuthResponse(token);
    }
}
