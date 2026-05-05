package com.fatec.labora.api.service;

import com.fatec.labora.domain.AuthToken;
import com.fatec.labora.domain.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.io.UnsupportedEncodingException;

@Service
public class EmailService {

    private final JavaMailSender emailSender;
    private final EmailTemplateService templateService;
    private final AuthTokenService authTokenService;

    private static final String FROM = "labify.contato@gmail.com";
    private static final String FROM_NAME = "Labify";

    public static final String URL = "http://localhost:8080";

    public EmailService(JavaMailSender emailSender,
                        EmailTemplateService templateService, AuthTokenService authTokenService) {
        this.emailSender = emailSender;
        this.templateService = templateService;
        this.authTokenService = authTokenService;
    }

    @Async
    public void sendEmail(String userEmail, String subject, String content) {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom(FROM, FROM_NAME);
            helper.setTo(userEmail);
            helper.setSubject(subject);
            helper.setText(content, true);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException("Erro ao enviar email");
        }

        emailSender.send(message);
    }

    public void sendVerificationEmail(User user) {
        String subject = "Labify - Verifique seu e-mail";

        AuthToken token = authTokenService.createEmailVerificationToken(user);

        String verificationUrl = URL + "/users/verify-account?token=" + token.getToken();

        Context context = new Context();
        context.setVariable("name", user.getName());
        context.setVariable("url", verificationUrl);

        String content = templateService.processTemplate("email/verification-email", context);

        sendEmail(user.getEmail(), subject, content);
    }
}