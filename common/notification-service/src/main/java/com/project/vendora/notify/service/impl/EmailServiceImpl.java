package com.project.vendora.notify.service.impl;

import com.project.vendora.notify.model.request.EmailDetails;
import com.project.vendora.notify.service.EmailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final TemplateEngine templateEngine;

    @Value("${sendgrid.api.key}")
    private String sendGridApiKey;

    @Value("${spring.mail.username}")
    private String emailSender;

    @Override
    @RabbitListener(queues = "${rabbitmq.queue.email.name}")
    public void sendActivationEmail(EmailDetails emailDetails) {

        String token = UUID.randomUUID().toString();
        String expirationTime = LocalDateTime.now().plusHours(24).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String resetPasswordLink = String.format("http://192.168.50.112:5050/api/v1/auth/activate?email=%s&token=%s&expiresAt=%s",
                emailDetails.getRecipient(), token, expirationTime);

        Context context = new Context();
        context.setVariable("resetPasswordLink", resetPasswordLink);
        String activate = templateEngine.process("activate_user.html", context);

        sendHtmlEmail(emailDetails.getRecipient(), emailDetails.getSubject(), activate);
    }

    private void sendHtmlEmail(String to, String subject, String content) {
        Email from = new Email(emailSender);
        Email toEmail = new Email(to);
        Content emailContent = new Content("text/html", content);
        Mail mail = new Mail(from, subject, toEmail, emailContent);

        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            log.info("Email sent successfully. Status Code: {}, Response Body: {}", response.getStatusCode(), response.getBody());
        } catch (IOException ex) {
            log.error("Failed to send email to {}: {}", to, ex.getMessage());
            throw new RuntimeException("Failed to send email", ex);
        }
    }
}
