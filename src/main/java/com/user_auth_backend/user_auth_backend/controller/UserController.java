package com.user_auth_backend.user_auth_backend.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.user_auth_backend.user_auth_backend.model.User;
import com.user_auth_backend.user_auth_backend.repository.UserRepository;

import java.time.Duration;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
  @Autowired private UserRepository userRepository;

    @GetMapping("/me")
    public Map<String, Object> getProfile(Authentication authentication) {
        String email = (String) authentication.getPrincipal();
        User user = userRepository.findByEmail(email).orElseThrow();

        Long lastSessionMinutes = null;
        if (user.getLastLoginTime() != null && user.getLastLogoutTime() != null) {
            lastSessionMinutes = Duration.between(user.getLastLoginTime(), user.getLastLogoutTime()).toMinutes();
        }

        return Map.of(
                "name", user.getName(),
                "email", user.getEmail(),
                "role", user.getRole(),
                "lastLoginTime", user.getLastLoginTime(),
                "lastLogoutTime", user.getLastLogoutTime(),
                "lastSessionMinutes", lastSessionMinutes,
                "failedAttempts", user.getFailedAttempt(),
                "accountNonLocked", user.isAccountNonLocked()
        );
    }
  
}
