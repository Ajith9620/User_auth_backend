package com.user_auth_backend.user_auth_backend.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service
public class EmailService {

   @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String toEmail, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("ajaycd324@gmail.com"); // same as spring.mail.username
            message.setTo(toEmail);
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);
            System.out.println("Email sent successfully to: " + toEmail);
        } catch (Exception e) {
            System.out.println("Failed to send email: " + e.getMessage());
        }
    }
  
}
