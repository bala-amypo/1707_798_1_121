package com.example.demo.service;

import com.example.demo.dto.AuthRequest;

public interface UserService {
    String register(com.example.demo.dto.RegisterRequest request);
    String login(AuthRequest request);
}
