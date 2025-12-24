package com.example.demo.dto;

public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private String role;
    
    // Constructors
    public RegisterRequest() {}
    
    public RegisterRequest(String name, String email, String password, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    
    // Getters
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
    
    // Setters
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setRole(String role) { this.role = role; }
    
    // Builder pattern
    public static RegisterRequestBuilder builder() {
        return new RegisterRequestBuilder();
    }
    
    public static class RegisterRequestBuilder {
        private String name;
        private String email;
        private String password;
        private String role;
        
        public RegisterRequestBuilder name(String name) { this.name = name; return this; }
        public RegisterRequestBuilder email(String email) { this.email = email; return this; }
        public RegisterRequestBuilder password(String password) { this.password = password; return this; }
        public RegisterRequestBuilder role(String role) { this.role = role; return this; }
        
        public RegisterRequest build() {
            return new RegisterRequest(name, email, password, role);
        }
    }
}