package com.user_auth_backend.user_auth_backend.model;

public class LoginResponse {
    private String message;
    private String token;
    private String role;
    private String name;

    public LoginResponse(String message, String token, String role, String name) {
        this.message = message; this.token = token; this.role = role; this.name = name;
    }

    public String getMessage() { return message; }
    public String getToken() { return token; }
    public String getRole() { return role; }
    public String getName() { return name; }  
}
