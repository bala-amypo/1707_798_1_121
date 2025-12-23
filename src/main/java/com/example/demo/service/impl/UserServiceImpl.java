package com.example.demo.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @Override
    public String register(RegisterRequest request) {
        User user = new User();
        user.setName(request.name);
        user.setEmail(request.email);
        user.setPassword(encoder.encode(request.password));
        user.setRole(request.role);

        repo.save(user);
        return "User Registered Successfully";
    }
    @Override
public String login(AuthRequest request) {
    User user = repo.findByEmail(request.email)
            .orElseThrow(() -> new RuntimeException("User not found"));

    if (!encoder.matches(request.password, user.getPassword())) {
        throw new RuntimeException("Invalid password");
    }

    return user.getEmail();
}

}
