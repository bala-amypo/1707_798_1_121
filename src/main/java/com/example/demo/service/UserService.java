package com.example.demo.service;

import com.example.demo.dto.*;

public interface UserService {
    void register(RegisterRequest request);
    AuthResponse login(AuthRequest request);
}
