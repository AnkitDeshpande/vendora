package com.project.vendora.notify.controller;

import com.project.vendora.core.utility.MessageUtils;
import com.project.vendora.core.utility.SuccessResponse;
import com.project.vendora.notify.constant.SuccessMessage;
import com.project.vendora.notify.model.request.EmailDetails;
import com.project.vendora.notify.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final EmailService notificationService;

    private final MessageUtils messageUtils;

    @PostMapping("/send-activation-email")
    public ResponseEntity<SuccessResponse<Void>> sendActivationEmail(@RequestBody EmailDetails emailDetails) {
        notificationService.sendActivationEmail(emailDetails);
        return SuccessResponse.<Void>builder()
                .data(null)
                .statusCode(SuccessMessage.MAIL_SENT.getCode())
                .message(messageUtils.getMessage(SuccessMessage.MAIL_SENT.getMessage(), null))
                .build().getResponseEntity();
    }
}
