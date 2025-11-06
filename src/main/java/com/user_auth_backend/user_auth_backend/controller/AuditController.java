package com.user_auth_backend.user_auth_backend.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.user_auth_backend.user_auth_backend.model.AuditLog;
import com.user_auth_backend.user_auth_backend.repository.AuditLogRepository;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")

public class AuditController {
   @Autowired
    private AuditLogRepository auditLogRepository;

    // USER: See only their own audit logs
    @GetMapping("/user/audit")
    public List<AuditLog> getUserAudit(Authentication authentication) {
        String email = (String) authentication.getPrincipal();
        return auditLogRepository.findByEmailOrderByTimestampDesc(email);
    }

    // ADMIN: See all users' audit logs
    @GetMapping("/admin/audit")
    public List<AuditLog> getAllAudits() {
        return auditLogRepository.findAll();
    }
  
}
