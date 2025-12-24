package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.exception.ApiException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepo, BCryptPasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        Optional<User> existing = userRepo.findByEmail(user.getEmail());
        if (existing.isPresent()) {
            throw new ApiException("User with this email already exists");
        }

        if (user.getRole() == null) user.setRole("STAFF");
        // For tests, we can skip password encoding if needed
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepo.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email).orElseThrow(() -> new ApiException("User not found"));
    }
}
