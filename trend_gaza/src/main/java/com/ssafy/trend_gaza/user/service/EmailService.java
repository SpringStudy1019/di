package com.ssafy.trend_gaza.user.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String email;

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        super();
        // this.email = email;
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(String to, String sub, String text) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setFrom(email);
        mailMessage.setSubject(sub);
        mailMessage.setText(text);
        javaMailSender.send(mailMessage);
    }
}
