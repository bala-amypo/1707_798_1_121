package com.example.demo.security;

import com.example.demo.model.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component   // ✅ THIS LINE FIXES YOUR ERROR
public class JwtUtil {

    private static final String SECRET =
            "mysecretkeymysecretkeymysecretkeymysecretkey";

    private static final long EXPIRATION = 1000 * 60 * 60;

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateTokenForUser(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", user.getEmail());
        claims.put("role", user.getRole());
        claims.put("userId", user.getId());
        return generateToken(claims, user.getEmail());
    }

    public String extractUsername(String token) {
        return parseToken(token).getBody().getSubject();
    }

    public String extractRole(String token) {
        return (String) parseToken(token).getBody().get("role");
    }

    public Long extractUserId(String token) {
        Object id = parseToken(token).getBody().get("userId");
        return id == null ? null : Long.valueOf(id.toString());
    }

    public boolean isTokenValid(String token, String username) {
        return extractUsername(token).equals(username)
                && !parseToken(token).getBody().getExpiration().before(new Date());
    }

    public Jws<Claims> parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }
}
