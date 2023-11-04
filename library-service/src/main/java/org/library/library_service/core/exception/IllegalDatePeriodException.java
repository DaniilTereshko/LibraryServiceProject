package org.library.library_service.core.exception;

public class IllegalDatePeriodException extends RuntimeException{
    private String field;
    public IllegalDatePeriodException() {
    }

    public IllegalDatePeriodException(String message) {
        super(message);
    }

    public IllegalDatePeriodException(String message, String field) {
        super(message);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
