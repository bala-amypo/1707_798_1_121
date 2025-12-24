package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final Key key;
    private final long validityInMs;

    public JwtTokenProvider() {
        // Secret must be at least 256 bits for HS256
        String secret = "this_is_a_test_secret_key_must_be_long_enough_for_hmac_sha_which_is_long";
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.validityInMs = 3600000; // 1 hour
    }

    // Generate JWT token
    public String generateToken(Long userId, String email, String role) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("userId", userId);
        claims.put("role", role);

        Date now = new Date();
        Date expiry = new Date(now.getTime() + validityInMs);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Validate token
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // Extract email
    public String getEmailFromToken(String token) {
        return parseClaims(token).getSubject();
    }

    // Extract role
    public String getRoleFromToken(String token) {
        return (String) parseClaims(token).get("role");
    }

    // Extract userId
    public Long getUserIdFromToken(String token) {
        Object id = parseClaims(token).get("userId");
        return id instanceof Integer ? ((Integer) id).longValue() : (Long) id;
    }

    private Claims parseClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }
}
