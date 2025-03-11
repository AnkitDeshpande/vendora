package com.project.vendora.auth.constant;

import lombok.Getter;

@Getter
public enum MailConfigEnum {

    REGISTRATION_SUCCESS("email.registration.success.body");

    private final String value;

    MailConfigEnum(String value) {
        this.value = value;
    }
}
