package com.tyson.socialmedia.controller;

import com.tyson.socialmedia.utility.JwtUtil;
import com.tyson.socialmedia.DTO.LoginRequest;
import com.tyson.socialmedia.entity.Account;
import com.tyson.socialmedia.service.AccountService;
import com.tyson.socialmedia.DTO.JwtResponse;

import com.tyson.socialmedia.service.*;
import com.tyson.socialmedia.utility.JwtUtil;

import jakarta.validation.Valid;
import com.tyson.socialmedia.entity.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.core.io.Resource;
import java.nio.file.Files;
import org.springframework.http.HttpHeaders;
import com.tyson.socialmedia.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tyson.socialmedia.service.*; // Replace with your actual service package
import com.tyson.socialmedia.utility.JwtUtil; // Replace with your actual utility package
import com.tyson.socialmedia.DTO.*; // Replace with your actual DTO package
import com.tyson.socialmedia.DTO.*; // Replace with your actual DTO package

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173") // Adjust for your frontend origin
public class AuthController {
    
    @Autowired
    private AccountService accountService;

    // Temporary hardcoded user for simplicity
    private static final String HARDCODED_USERNAME = "testuser";
    private static final String HARDCODED_PASSWORD = "password";
}
/*
   @PostMapping("/login")
   @CrossOrigin(origins = "http://localhost:5173")
public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
    // Check if the user exists in the database
    Account account = accountService.findByUsername(loginRequest.getUsername());

    if (account != null && account.getPassword().equals(loginRequest.getPassword())) {
        // Generate token
        String token = JwtUtil.generateToken(loginRequest.getUsername());
        return ResponseEntity.ok(new JwtResponse(token));
    }

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
}


    @GetMapping("/protected")
    public ResponseEntity<?> protectedEndpoint(@RequestHeader("Authorization") String token) {
        try {
            // Validate token
            JwtUtil.validateToken(token.replace("Bearer ", ""));
            return ResponseEntity.ok("You have access!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }
}
*/