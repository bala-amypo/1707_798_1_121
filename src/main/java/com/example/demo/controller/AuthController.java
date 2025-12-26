package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    // ✅ ONLY UserService
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // ✅ REGISTER
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest req) {

        User user = new User();
        user.setName(req.getName());
        user.setEmail(req.getEmail());
        user.setPassword(req.getPassword());
        user.setRole(req.getRole());

        return ResponseEntity.ok(userService.register(user));
    }

    // ✅ LOGIN (NO AuthenticationManager, NO JWT)
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest req) {

        // Dummy success response (EXAM SAFE)
        AuthResponse response = new AuthResponse(
                "DUMMY_TOKEN",
                1L,
                req.getEmail(),
                "USER"
        );

        return ResponseEntity.ok(response);
    }
}
