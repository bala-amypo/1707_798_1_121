package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    // ✅ REGISTER
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest req) {

        User u = new User();
        u.setName(req.getName());
        u.setEmail(req.getEmail());
        u.setPassword(req.getPassword());
        u.setRole(req.getRole());

        userService.register(u);
        return "User registered successfully";
    }

    // ✅ LOGIN
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest req) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getEmail(),
                        req.getPassword()
                )
        );

        User u = userService.findByEmail(req.getEmail());

        // ✅ FIXED: PASS ALL 3 REQUIRED ARGUMENTS
        String token = jwtTokenProvider.generateToken(
                u.getId(),
                u.getEmail(),
                u.getRole()
        );

        AuthResponse res = new AuthResponse();
        res.setToken(token);
        res.setUserId(u.getId());
        res.setEmail(u.getEmail());
        res.setRole(u.getRole());

        return res;
    }
}
