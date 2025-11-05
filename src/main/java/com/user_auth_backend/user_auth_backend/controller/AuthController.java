package com.user_auth_backend.user_auth_backend.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.user_auth_backend.user_auth_backend.model.LoginRequest;
import com.user_auth_backend.user_auth_backend.model.LoginResponse;
import com.user_auth_backend.user_auth_backend.model.User;
import com.user_auth_backend.user_auth_backend.service.AuthService;
import com.user_auth_backend.user_auth_backend.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

  @Autowired private UserService userService;
    @Autowired private AuthService authService;

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public LoginResponse loginUser(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
  
}
