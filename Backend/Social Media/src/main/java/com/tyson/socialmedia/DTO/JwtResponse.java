package com.tyson.socialmedia.DTO;

public class JwtResponse {

    private String token;

    // Constructor
    public JwtResponse(String token) {
        this.token = token;
    }

    // Getter
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
