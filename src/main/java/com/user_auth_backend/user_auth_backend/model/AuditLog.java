package com.user_auth_backend.user_auth_backend.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "audit_logs")
public class AuditLog {
  
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String action; // LOGIN / LOGOUT
    private LocalDateTime timestamp;
    private String status; // SUCCESS / FAILURE
    private String ipAddress; // optional, for tracking

    public AuditLog() {}

    public AuditLog(String email, String action, String status, String ipAddress) {
        this.email = email;
        this.action = action;
        this.status = status;
        this.timestamp = LocalDateTime.now();
        this.ipAddress = ipAddress;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getIpAddress() { return ipAddress; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }
}
