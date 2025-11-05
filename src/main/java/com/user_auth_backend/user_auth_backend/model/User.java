package com.user_auth_backend.user_auth_backend.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String role = "USER";

    private boolean accountNonLocked = true;

    private int failedAttempt = 0;

    private LocalDateTime lastLoginTime;
    private LocalDateTime lastLogoutTime;

    public User() {}

    public User(String name, String email, String password, String role) {
        this.name = name; this.email = email; this.password = password; this.role = role;
    }

    // Getters & setters (omitted here for brevity — include standard ones in code)
    // ... generate all getters & setters in your IDE
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public boolean isAccountNonLocked() { return accountNonLocked; }
    public void setAccountNonLocked(boolean accountNonLocked) { this.accountNonLocked = accountNonLocked; }
    public int getFailedAttempt() { return failedAttempt; }
    public void setFailedAttempt(int failedAttempt) { this.failedAttempt = failedAttempt; }
    public LocalDateTime getLastLoginTime() { return lastLoginTime; }
    public void setLastLoginTime(LocalDateTime lastLoginTime) { this.lastLoginTime = lastLoginTime; }
    public LocalDateTime getLastLogoutTime() { return lastLogoutTime; }
    public void setLastLogoutTime(LocalDateTime lastLogoutTime) { this.lastLogoutTime = lastLogoutTime; }

}
