package com.user_auth_backend.user_auth_backend.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;

import com.user_auth_backend.user_auth_backend.model.User;
import com.user_auth_backend.user_auth_backend.repository.UserRepository;
@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {
  @Autowired private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> listAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}/attempts")
    public User getUserAttempts(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @PostMapping("/users/{id}/promote")
    public String promoteUser(@PathVariable Long id) {
        var u = userRepository.findById(id).orElseThrow();
        u.setRole("ADMIN");
        userRepository.save(u);
        return "Promoted to ADMIN";
    }

    @PostMapping("/users/{id}/unlock")
    public String unlockUser(@PathVariable Long id) {
        var u = userRepository.findById(id).orElseThrow();
        u.setFailedAttempt(0);
        u.setAccountNonLocked(true);
        userRepository.save(u);
        return "User unlocked";
    }
  
}
