package com.dashboard.api.Service;

import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.dashboard.api.Entity.DashboardUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MailService {
    private final JavaMailSender javaMailSender;

    public void sendVerificationMail(DashboardUser user, String verificationCode) throws Exception {
        try {
            MimeMessage email = javaMailSender.createMimeMessage();
            email.setRecipient(RecipientType.TO, new InternetAddress(user.getEmail(), user.getUsername()));
            email.setSubject("Dashboard User Account Verification");
            email.setContent("<a href=\"http://dashboard.io:8080/api/auth/verify/" + user.getId() + "/" + verificationCode + "\">Click this link to verify your account</a>", "text/html");
    
            javaMailSender.send(email);
        } catch (Exception ex) {
            throw ex;
        }
    }
}
