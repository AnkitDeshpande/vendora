package com.project.vendora.core.constant;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Headers {
    TENANT_ID("tenant"),
    AUTH_TOKEN("token");

    private final String value;
}
