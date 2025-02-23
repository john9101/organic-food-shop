package com.spring.project.organicfoodshop.service;

import brevo.ApiClient;
import brevo.ApiException;
import brevo.Configuration;
import brevo.auth.ApiKeyAuth;
import brevoApi.TransactionalEmailsApi;
import brevoModel.SendSmtpEmail;
import brevoModel.SendSmtpEmailSender;
import brevoModel.SendSmtpEmailTo;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@Service
@RequiredArgsConstructor
public class EmailService {

    @Value("${brevo.api.key}")
    private String brevoApiKey;

    @Value("${brevo.sender.email}")
    private String brevoSenderEmail;

    @Value("${brevo.sender.name}")
    private String brevoSenderName;

//    private final JavaMailSender javaMailSender;
//
//    private final SpringTemplateEngine springTemplateEngine;

//    @Async
//    public void sendEmailWithTemplate(String subject, String templateName, Context context, boolean isMultipart, String... receivers) throws MessagingException {
//        MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
//        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, isMultipart, StandardCharsets.UTF_8.name());
//        mimeMessageHelper.setTo(receivers);
//        mimeMessageHelper.setSubject(subject);
//        String htmlContent = springTemplateEngine.process(templateName, context);
//        mimeMessageHelper.setText(htmlContent, true);
//        this.javaMailSender.send(mimeMessage);
//    }

//    @Async
    public void sendEmailWithTemplate(Long templateId, Map<String, Object> templateParams, String... emailRecipients) throws MessagingException {
        ApiClient apiClient = Configuration.getDefaultApiClient();
        ApiKeyAuth apiKeyAuth = (ApiKeyAuth) apiClient.getAuthentication("api-key");
        apiKeyAuth.setApiKey(brevoApiKey);
        TransactionalEmailsApi transactionalEmailsApi = new TransactionalEmailsApi(apiClient);
        SendSmtpEmailSender sendSmtpEmailSender = new SendSmtpEmailSender().email(brevoSenderEmail).name(brevoSenderName);
        List<SendSmtpEmailTo> sendSmtpEmailTos = Arrays.stream(emailRecipients).map(emailRecipient -> new SendSmtpEmailTo().email(emailRecipient)).collect(Collectors.toList());
        SendSmtpEmail sendSmtpEmail = new SendSmtpEmail()
                .sender(sendSmtpEmailSender)
                .to(sendSmtpEmailTos)
                .templateId(templateId)
                .params(templateParams);

        try {
            transactionalEmailsApi.sendTransacEmail(sendSmtpEmail);
        }catch (ApiException exception){
            System.err.println(exception.getMessage());
        }
    }
}
