package com.project.vendora.notify.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessMessage {

    MAIL_SENT(200, "mail.sent.success");

    /**
     * The code.
     */
    private final int code;

    /**
     * The message.
     */
    private final String message;
}
