package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {
    User register(String name, String email, String password);
    String login(String email, String password);
}
