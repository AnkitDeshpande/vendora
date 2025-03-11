package com.project.vendora.core.utility;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class MessageUtils {

    private final MessageSource messageSource;

    /**
     * Get the message from properties file based on current local.
     *
     * @param message - The message key
     * @param args    -    The argument list
     * @return String - The message
     */
    public String getMessage(final String message, final Object[] args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(message, args, locale);
    }
}