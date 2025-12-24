package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.exception.ApiException;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserServiceImpl implements UserService {
    private final UserRepository repo;
    private final BCryptPasswordEncoder encoder;

    public UserServiceImpl(UserRepository repo, BCryptPasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @Override
    public User register(User u) {
        if (repo.findByEmail(u.getEmail()).isPresent())
            throw new ApiException("User with email exists");
        if (u.getRole() == null) u.setRole("STAFF");
        u.setPassword(encoder.encode(u.getPassword()));
        return repo.save(u);
    }

    @Override
    public User findByEmail(String email) {
        return repo.findByEmail(email).orElseThrow(() -> new ApiException("User not found"));
    }
}
