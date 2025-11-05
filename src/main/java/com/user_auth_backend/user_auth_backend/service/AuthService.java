package com.user_auth_backend.user_auth_backend.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;
import com.user_auth_backend.user_auth_backend.model.LoginRequest;
import com.user_auth_backend.user_auth_backend.model.LoginResponse;
import com.user_auth_backend.user_auth_backend.model.User;
import com.user_auth_backend.user_auth_backend.repository.UserRepository;
import com.user_auth_backend.user_auth_backend.security.JwtTokenUtil;
@Service
public class AuthService {
   @Autowired private UserRepository userRepository;
    @Autowired private JwtTokenUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private static final int MAX_FAILED_ATTEMPTS = 3;

    public LoginResponse login(LoginRequest loginRequest) {
        Optional<User> userOpt = userRepository.findByEmail(loginRequest.getEmail());
        if (userOpt.isEmpty()) {
            return new LoginResponse("Invalid email or password", null, null, null);
        }
        User user = userOpt.get();
        if (!user.isAccountNonLocked()) {
            return new LoginResponse("Account locked after 3 failed attempts!", null, user.getRole(), user.getName());
        }
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            handleFailedLogin(user);
            return new LoginResponse("Invalid credentials", null, null, null);
        }
        // success:
        user.setFailedAttempt(0);
        user.setAccountNonLocked(true);
        user.setLastLoginTime(LocalDateTime.now());
        userRepository.save(user);
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
        return new LoginResponse("Login successful", token, user.getRole(), user.getName());
    }

    private void handleFailedLogin(User user) {
        int newAttempts = user.getFailedAttempt() + 1;
        user.setFailedAttempt(newAttempts);
        if (newAttempts >= MAX_FAILED_ATTEMPTS) {
            user.setAccountNonLocked(false);
        }
        userRepository.save(user);
    }
  
}
