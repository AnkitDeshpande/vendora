package com.project.vendora.auth.service;

import com.project.vendora.auth.model.request.EmailDetails;

public interface NotificationService {
    void sendActivationEmail(EmailDetails emailDetails);
}