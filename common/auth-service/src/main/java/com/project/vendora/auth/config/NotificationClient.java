package com.project.vendora.auth.config;

import com.project.vendora.auth.model.request.EmailDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "notificationClient", url = "http://localhost:5052/notifyApp/api/v1/notification")
public interface NotificationClient {
    @PostMapping("/send-activation-email")
    void sendEmail(@RequestHeader(value = "user-agent") String userAgent, @RequestBody EmailDetails emailDetails);
}