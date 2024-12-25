package com.example.jwt.controller;

import com.example.jwt.entity.User;
import com.example.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    // Registration API to hash password and store user
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Hash the password
        userService.saveUser(user); // Save user to DB (if needed)
        return "User registered successfully!";
    }

    // Login API to authenticate user and generate JWT
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User loginRequest) {
        String token = userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return response;
    }
}
