package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.exception.ApiException;

public interface UserService {
    User register(User user) throws ApiException;
    User findByEmail(String email) throws ApiException;
}
