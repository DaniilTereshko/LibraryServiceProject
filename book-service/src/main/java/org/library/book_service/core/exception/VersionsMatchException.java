package org.library.book_service.core.exception;

public class VersionsMatchException extends RuntimeException{
    public VersionsMatchException() {
    }

    public VersionsMatchException(String message) {
        super(message);
    }
}
