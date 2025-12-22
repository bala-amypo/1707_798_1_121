package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.exception.ApiException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwt;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwt) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwt = jwt;
    }

    @Override
    public void register(RegisterRequest r) {
        if (userRepository.existsByEmail(r.getEmail()))
            throw new ApiException("Email already exists");

        User user = new User(
                r.getName(),
                r.getEmail(),
                passwordEncoder.encode(r.getPassword()),
                r.getRole()
        );
        userRepository.save(user);
    }

    @Override
    public AuthResponse login(AuthRequest r) {
        User user = userRepository.findByEmail(r.getEmail())
                .orElseThrow(() -> new ApiException("Invalid credentials"));

        if (!passwordEncoder.matches(r.getPassword(), user.getPassword()))
            throw new ApiException("Invalid credentials");

        String token = jwt.generateToken(user.getEmail(), user.getRole());
        return new AuthResponse(token, user.getEmail(), user.getRole());
    }
}
