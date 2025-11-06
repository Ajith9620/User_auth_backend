package com.user_auth_backend.user_auth_backend.service;
import com.user_auth_backend.user_auth_backend.model.AuditLog;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.user_auth_backend.user_auth_backend.repository.AuditLogRepository;


@Service
public class AuditService {

  @Autowired
    private AuditLogRepository auditLogRepository;

    // Record login or logout action
    public void record(String email, String action, String status, HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        AuditLog log = new AuditLog(email, action, status, ip);
        auditLogRepository.save(log);
    }
  
}
