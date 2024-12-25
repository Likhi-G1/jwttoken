package com.example.jwt.service;

import com.example.jwt.entity.User;

public interface UserService {
    String generateToken(User user);
    boolean validateToken(String token);
}
