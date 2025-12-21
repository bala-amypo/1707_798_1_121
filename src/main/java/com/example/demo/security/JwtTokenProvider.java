package com.example.demo.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    private final String SECRET = "examsecretkey";

    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public String getEmailFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
