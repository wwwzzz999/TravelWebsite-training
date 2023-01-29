package com.zjtec.travel.service;

public interface EmailService {
    void sendEmail(String sendTo, String title, String content);
    void setSmtpHost(String smtpHost);
    void setUsername(String username);
    void setPassword(String password);
    void setSmtpAuth(String smtpAuth);
}
