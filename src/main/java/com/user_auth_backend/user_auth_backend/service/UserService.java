package com.user_auth_backend.user_auth_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.user_auth_backend.user_auth_backend.model.User;
import com.user_auth_backend.user_auth_backend.repository.UserRepository;

@Service
public class UserService {
  @Autowired private UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return "Email already exists!";
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole("USER");
        userRepository.save(user);
        return "User registered successfully!";
    }
  
}
