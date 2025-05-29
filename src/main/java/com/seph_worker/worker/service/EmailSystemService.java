package com.seph_worker.worker.service;


import com.seph_worker.worker.core.entity.Emails.CoreEmailHtml;
import com.seph_worker.worker.core.exception.ResourceNotFoundException;
import com.seph_worker.worker.repository.EmailsRepository.EmailHtmlRepository;
import com.seph_worker.worker.repository.EmailsRepository.EmailSystemRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Map;

@Service
@AllArgsConstructor
public class EmailSystemService {

    private final Map<String, JavaMailSender> mailSenderMap;
    private final EmailHtmlRepository emailHtmlRepository;
    private final EmailSystemRepository emailSystemRepository;
    public void sendHtmlToken(String sender, String to, String subject, String typeHtml, Map<String,String> replace, String filePath) throws MessagingException {
        JavaMailSender mailSender = mailSenderMap.get(sender);
        if (mailSender == null) {
            throw new IllegalArgumentException("Remitente no válido: " + sender);
        }

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true,"UTF-8");

         CoreEmailHtml emailHtml = emailHtmlRepository.findByName(typeHtml)
                 .orElseThrow(()-> new ResourceNotFoundException("No se encontro el HTML"));


        String htmlContent = emailHtml.getHtml();
        for (Map.Entry<String, String> entry : replace.entrySet()) {
            htmlContent = htmlContent.replace(entry.getKey(), entry.getValue());
        }


        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);
        helper.setFrom(((JavaMailSenderImpl) mailSender).getUsername());


        if (filePath != null) {
            FileSystemResource file = new FileSystemResource(new File(filePath));
            helper.addAttachment(file.getFilename(), file);
        }

        mailSender.send(message);
    }
}
