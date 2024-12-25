package com.example.jwt.service.impl;

import com.example.jwt.entity.User;
import com.example.jwt.service.UserService;
import com.example.jwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(JwtUtil jwtUtil, BCryptPasswordEncoder passwordEncoder) {
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    // Generate JWT token for a valid user
    @Override
    public String generateToken(User user) {
        // In a real-world application, this would involve checking the user credentials
        // from the database and using passwordEncoder to validate the password.

        // For simplicity, assuming user is valid and password check is done:
        return jwtUtil.generateToken(user.getUsername());
    }

    // Validate the JWT token and check if it's valid (not expired)
    @Override
    public boolean validateToken(String token) {
        try {
            String username = jwtUtil.validateToken(token);
            // If token is valid, return true
            return username != null;
        } catch (Exception e) {
            // If token is invalid (expired or malformed), return false
            return false;
        }
    }
}
