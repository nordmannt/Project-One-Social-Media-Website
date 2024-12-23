package com.tyson.socialmedia.controller;


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

import java.util.Map;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;





 //SocialMediaController class for CRUD functionality interfacing with service class
 @RestController
 @RequestMapping("/api/auth") 
 @CrossOrigin(origins = "http://localhost:5173")
 public class AccountController {

    //dependency injection to obtain an instance of the AccountService class
    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthService authService;

   
    private LoginRequest loginRequest;

   
    private JwtResponse jwtResponse;


    //dependency injection to obtain an instance of the MessageService class
    @Autowired
    private MessageService messageService;

    @GetMapping("/home")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<Resource> rootEndpoint() {
        Resource resource = new ClassPathResource("static/home.html");

        //String content = new String(Files.readAllBytes(resource.getFile().toPath()));

        return ResponseEntity
        .status(HttpStatus.OK)
        .header(HttpHeaders.CONTENT_TYPE, "text/html")
        .body(resource);
    }

    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Check if the user exists in the database
        Account account = accountService.findByUsername(loginRequest.getUsername());
    
        if (account != null && account.getPassword().equals(loginRequest.getPassword())) {
            // Generate token
            String token = JwtUtil.generateToken(loginRequest.getUsername());
    
            // Create a response with token and userId
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("userId", account.getAccountId()); // Assuming accountId is the primary key
    
            return ResponseEntity.ok(response);
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



     
     @GetMapping("/account/search")
     public ResponseEntity<List<AccountDTO>> searchUsers(@RequestParam String query) {
         List<AccountDTO> users = accountService.searchUsers(query);
         return ResponseEntity.ok(users);
     }


      
    //POST registerUser method to register user accounts, validates account information  
    @PostMapping("/register")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<?> registerUser(@RequestBody Account account){
            String username = account.getUsername();
            boolean exists = accountService.accountExists(username);
            if(exists){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Account already exists.");
               
              
          
            }
           
            Account createdAccount = accountService.register(account);
            return ResponseEntity.status(HttpStatus.CREATED).body("Registration successful!");}
        }
    
  