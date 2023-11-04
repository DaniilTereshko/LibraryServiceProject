package org.library.library_service.core.exception;

public class VersionsMatchException extends RuntimeException{
    public VersionsMatchException() {
    }

    public VersionsMatchException(String message) {
        super(message);
    }
}
