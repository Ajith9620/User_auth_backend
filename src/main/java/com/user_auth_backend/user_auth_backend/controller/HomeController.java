package com.user_auth_backend.user_auth_backend.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Backend is running! You can now call /api/auth/login or other APIs.";
    }
}