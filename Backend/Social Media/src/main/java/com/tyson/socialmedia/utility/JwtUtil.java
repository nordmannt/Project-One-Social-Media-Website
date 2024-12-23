package com.tyson.socialmedia.utility;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;

import java.util.Date;

public class JwtUtil {
    private static final String SECRET_KEY = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"; // Keep this secret!
    private static final long EXPIRATION_TIME = 3600000; // 1 hour

    // Generate a JWT token
    public static String generateToken(String username) {
        
            String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours expiration
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
        
            System.out.println("Generated Token: " + token); // Log the token
            return token;
        }
        
    

    // Validate and extract claims from the token
    public static Claims validateToken(String token) throws JwtException {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
