package com.project.vendora.core.exception;

import com.project.vendora.core.utility.ErrorResponse;
import com.project.vendora.core.utility.MessageUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final HttpServletRequest httpServletRequest;

    private final MessageUtils messageUtils;

    @ExceptionHandler(value = {VendoraException.class})
    protected ResponseEntity<ErrorResponse<VendoraException>> handleConflict(final VendoraException exception) {
        ErrorResponse<VendoraException> errorResponse = new ErrorResponse<>();
        errorResponse.setStatusCode(exception.getCode());
        errorResponse.setMessage(messageUtils.getMessage(exception.getMessage(), null));
        errorResponse.setUrl(httpServletRequest.getRequestURI());
        errorResponse.setExceptionTrace(exception.toString());
        return errorResponse.getResponseEntity();
    }
}
