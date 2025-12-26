package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // ❌ Disable CSRF
            .csrf(csrf -> csrf.disable())

            // ✅ Allow EVERYTHING (Swagger + APIs)
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
            )

            // ❌ Disable default login
            .formLogin(form -> form.disable())

            // ❌ Disable basic auth
            .httpBasic(basic -> basic.disable());

        return http.build();
    }

    // ✅ REQUIRED for UserServiceImpl
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
