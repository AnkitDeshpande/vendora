package com.project.vendora.auth.config;

import com.project.vendora.auth.model.request.EmailDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service", url = "${notification.service.url}")
public interface NotificationClient {

    @PostMapping("/api/notifications/send-email")
    void sendEmail(@RequestBody EmailDetails emailDetails);
}