package com.project.vendora.auth.service.impl;

import com.project.vendora.auth.config.NotificationClient;
import com.project.vendora.auth.model.request.EmailDetails;
import com.project.vendora.auth.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationClient emailClient;

    @Override
    public void sendActivationEmail(EmailDetails emailDetails) {
        emailClient.sendEmail(emailDetails);
    }
}
