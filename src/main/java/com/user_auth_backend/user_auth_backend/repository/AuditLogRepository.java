package com.user_auth_backend.user_auth_backend.repository;
import com.user_auth_backend.user_auth_backend.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AuditLogRepository extends JpaRepository<AuditLog,Long>{
   List<AuditLog> findByEmailOrderByTimestampDesc(String email);
}
