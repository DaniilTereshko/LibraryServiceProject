package org.library.user_server.core.exception;

public class LoginException extends RuntimeException{
    public LoginException() {
    }

    public LoginException(String message) {
        super(message);
    }
}
