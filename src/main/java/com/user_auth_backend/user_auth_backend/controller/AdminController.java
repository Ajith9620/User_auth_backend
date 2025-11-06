package com.user_auth_backend.user_auth_backend.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/users/{id}/promote")
    public String promoteUser(@PathVariable Long id) {
        var u = userRepository.findById(id).orElseThrow();
        u.setRole("ADMIN");
        userRepository.save(u);
        return "Promoted to ADMIN";
    }

     @PutMapping("/users/{id}/demote")
    public String demoteUser(@PathVariable Long id) {
        var u = userRepository.findById(id).orElseThrow();
        u.setRole("USER");
        userRepository.save(u);
        return "Demoted to USER";
    }

    @PutMapping("/users/{id}/unlock")
    public String unlockUser(@PathVariable Long id) {
        var u = userRepository.findById(id).orElseThrow();
        u.setFailedAttempt(0);
        u.setAccountNonLocked(true);
        userRepository.save(u);
        return "User unlocked";
    }
  
}
