package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User register(User user) {
        // Implement save logic here (e.g., repository.save(user))
        return user;
    }

    @Override
    public User login(String email, String password) {
        // Implement login logic here (e.g., repository.findByEmailAndPassword)
        return new User(); // dummy return
    }
}
