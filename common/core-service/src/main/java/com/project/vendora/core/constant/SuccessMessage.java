package com.project.vendora.core.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessMessage {

    SIGN_UP_SUCCESS(200, "sign.up.success"),
    LOGIN_SUCCESS(200, "login.success"),
    LOGOUT_SUCCESS(200, "logout.success"),
    USER_ACTIVATED(200, "user.activated.success"),
    RESET_PASSWORD(200, "reset.password");


    /**
     * The code.
     */
    private final int code;

    /**
     * The message.
     */
    private final String message;
}
