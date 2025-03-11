package com.project.vendora.core.exception;

/**
 * Exception to highlight a user does not have a verified email address.
 */
public class UserNotVerifiedException extends RuntimeException {

    /**
     * Did we send a new email?
     */
    private final boolean newEmailSent;

    /**
     * Constructor.
     *
     * @param newEmailSent Was a new email sent?
     */
    public UserNotVerifiedException(boolean newEmailSent) {
        this.newEmailSent = newEmailSent;
    }

    /**
     * Was a new email sent?
     *
     * @return True if it was, false otherwise.
     */
    public boolean isNewEmailSent() {
        return newEmailSent;
    }

}
