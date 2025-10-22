package com.example.AIResumeBuilder.controller;

import com.example.AIResumeBuilder.dto.LoginResponseDto;
import com.example.AIResumeBuilder.dto.UserLoginDto;
import com.example.AIResumeBuilder.dto.UserRegistrationDto;
import com.example.AIResumeBuilder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserLoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDto registrationDto) {
        // No try-catch needed, GlobalExceptionHandler will handle it
        userService.registerUser(registrationDto);
        return ResponseEntity.status(201).body("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginDto user) {
        // No try-catch needed
        String token = userService.loginUser(user.getEmail(), user.getPassword());
        // Return the token in a DTO
        return ResponseEntity.ok(new LoginResponseDto(token));
    }
}