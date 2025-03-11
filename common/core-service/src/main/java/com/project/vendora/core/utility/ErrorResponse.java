package com.project.vendora.core.utility;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;


@Setter
@Getter
@JsonPropertyOrder(value = {"statusCode", "message", "url", "exceptionTrace"})
public class ErrorResponse<T> {

    /**
     * Exception trace.
     */
    private String exceptionTrace;

    /**
     * url path of the request.
     */
    private String url;

    /**
     * Status code.
     */
    private Integer statusCode;

    /**
     * Error message.
     */
    private String message;

    public ErrorResponse(String exceptionTrace, String url, Integer statusCode, String message) {
        this.exceptionTrace = exceptionTrace;
        this.url = url;
        this.statusCode = statusCode;
        this.message = message;
    }

    public ErrorResponse() {
    }

    /**
     * Get response entity.
     *
     * @return ResponseEntity
     */
    @JsonIgnore
    public ResponseEntity<ErrorResponse<T>> getResponseEntity() {
        return ResponseEntity.ok(this);
    }

}