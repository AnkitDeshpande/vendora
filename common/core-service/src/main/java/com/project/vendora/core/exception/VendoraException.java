package com.project.vendora.core.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VendoraException extends RuntimeException {

    /**
     * The exception code.
     */
    private int code;

    /**
     * The exception message.
     */
    private String message;

    public VendoraException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public VendoraException(String message, int code, String message1) {
        super(message);
        this.code = code;
        this.message = message1;
    }

    public VendoraException(String message, Throwable cause, int code, String message1) {
        super(message, cause);
        this.code = code;
        this.message = message1;
    }

    public VendoraException(Throwable cause, int code, String message) {
        super(cause);
        this.code = code;
        this.message = message;
    }

    public VendoraException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code, String message1) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.message = message1;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
