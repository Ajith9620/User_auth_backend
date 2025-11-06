package com.user_auth_backend.user_auth_backend.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.user_auth_backend.user_auth_backend.model.User;
import com.user_auth_backend.user_auth_backend.repository.UserRepository;
import com.user_auth_backend.user_auth_backend.service.AuditService;
import com.user_auth_backend.user_auth_backend.service.EmailService;

import jakarta.servlet.http.HttpServletRequest;
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class LogoutController {

    @Autowired private EmailService emailService;
    @Autowired private AuditService auditService;
    @Autowired private HttpServletRequest request;
    @Autowired private UserRepository userRepository;

    @PostMapping("/logout")
    public String logout(Authentication authentication) {
        if (authentication == null) return "No user authenticated";
        String email = (String) authentication.getPrincipal();
        User user = userRepository.findByEmail(email).orElseThrow();
        user.setLastLogoutTime(LocalDateTime.now());
        userRepository.save(user);
        auditService.record(email, "LOGOUT", "SUCCESS", request);
        emailService.sendMail(
    user.getEmail(),
    "Logout Notification - UserAuthSystem",
    "Hello " + user.getName() + ",\n\nYou have logged out at " + LocalDateTime.now()
    + ".\n\nIf this wasn't you, please secure your account immediately.\n\n- UserAuthSystem"
);

        return "Logged out";
    }
  
}
