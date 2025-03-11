package com.project.vendora.notify.service;


import com.project.vendora.notify.model.request.EmailDetails;

public interface EmailService {

    void sendActivationEmail(EmailDetails emailDetails);
}
