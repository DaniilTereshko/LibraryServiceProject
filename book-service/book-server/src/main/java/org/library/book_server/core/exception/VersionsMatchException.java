package org.library.book_server.core.exception;

public class VersionsMatchException extends RuntimeException{
    public VersionsMatchException() {
    }

    public VersionsMatchException(String message) {
        super(message);
    }
}
