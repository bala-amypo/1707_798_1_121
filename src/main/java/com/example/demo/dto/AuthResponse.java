package com.example.demo.dto;

public class AuthResponse {
    private String token;
    private String email;
    private String role;
    
    // Constructors
    public AuthResponse() {}
    
    public AuthResponse(String token, String email, String role) {
        this.token = token;
        this.email = email;
        this.role = role;
    }
    
    // Getters
    public String getToken() { return token; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    
    // Setters
    public void setToken(String token) { this.token = token; }
    public void setEmail(String email) { this.email = email; }
    public void setRole(String role) { this.role = role; }
    
    // Builder pattern
    public static AuthResponseBuilder builder() {
        return new AuthResponseBuilder();
    }
    
    public static class AuthResponseBuilder {
        private String token;
        private String email;
        private String role;
        
        public AuthResponseBuilder token(String token) { this.token = token; return this; }
        public AuthResponseBuilder email(String email) { this.email = email; return this; }
        public AuthResponseBuilder role(String role) { this.role = role; return this; }
        
        public AuthResponse build() {
            return new AuthResponse(token, email, role);
        }
    }
}