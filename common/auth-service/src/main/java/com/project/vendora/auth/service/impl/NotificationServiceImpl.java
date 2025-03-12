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
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36";
        emailClient.sendEmail(userAgent,emailDetails);
    }
}
