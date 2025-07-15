package com.seph_worker.worker.core.dto;


import com.seph_worker.worker.repository.Core.UserRoleModule.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@AllArgsConstructor
public class MailConfig {

    private final UserRepository userRepository;

    @Bean
    public Map<String, JavaMailSender> mailSenderMap() {
        Map<String, JavaMailSender> mailSenders = new HashMap<>();
        userRepository.getEmailsSystem().forEach(email->{
            mailSenders.put(email.get("name"),createMailSender(email.get("email"),email.get("password")));
        });
        return mailSenders;
    }

    private JavaMailSender createMailSender(String email, String password) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.office365.com");
        mailSender.setPort(587);
        mailSender.setUsername(email);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        return mailSender;
    }
}