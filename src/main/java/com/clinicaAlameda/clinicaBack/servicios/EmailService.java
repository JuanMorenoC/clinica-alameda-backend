package com.clinicaAlameda.clinicaBack.servicios;

import org.springframework.stereotype.Service;

import com.clinicaAlameda.clinicaBack.entidades.EmailModel;
import com.clinicaAlameda.clinicaBack.entidades.StatusEmail;
import com.clinicaAlameda.clinicaBack.repositorios.EmailRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmailService {
    @Autowired
    EmailRepository emailRepository;

    @Autowired
    private JavaMailSender emailSender;

    public EmailModel sendEmail(EmailModel emailModel) {
        emailModel.setSendDateEmail(LocalDateTime.now());
        
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailModel.getEmailFrom());
        message.setTo(emailModel.getEmailTo());
        message.setSubject(emailModel.getSubject());
        message.setText(emailModel.getText());
        emailSender.send(message);

        emailModel.setStatusEmail(StatusEmail.SENT);
        return emailRepository.save(emailModel);
    }

    public Page<EmailModel> findAll(Pageable pageable) {
        return  emailRepository.findAll(pageable);
    }

    public Optional<EmailModel> findById(UUID emailId) {
        return emailRepository.findById(emailId);
    }
}
