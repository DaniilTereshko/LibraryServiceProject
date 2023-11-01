package org.library.book_server.endpoints.web.exception_hendler;

import jakarta.validation.ConstraintViolationException;
import org.library.book_server.core.exception.IncorrectDataException;
import org.library.book_server.core.exception.NotFoundException;
import org.library.book_server.core.exception.VersionsMatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.taskmanager.base_package.enums.ErrorType;
import org.taskmanager.base_package.errors.ErrorResponse;
import org.taskmanager.base_package.errors.StructuredErrorResponse;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class UserServiceExceptionHandler {
    private static final String NOT_READABLE = "Запрос содержит некорректные данные. Измените запрос и отправьте его еще раз";
    private static final String INTERNAL_SERVER_ERROR = "Внутренняя ошибка сервера. Сервер не смог корректно обработать запрос";
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StructuredErrorResponse> handleInvalidArgument(ConstraintViolationException exception){
        StructuredErrorResponse response = new StructuredErrorResponse(ErrorType.STRUCTURED_ERROR, new HashMap<>());
        Map<String, String> errors = response.getErrors();
        exception.getConstraintViolations()
                .forEach(v -> errors.put(v.getPropertyPath().toString(), v.getMessage()));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(){
        ErrorResponse response = new ErrorResponse(ErrorType.ERROR, NOT_READABLE);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({RuntimeException.class, Error.class})
    public ResponseEntity<ErrorResponse> handleInnerError(){
        ErrorResponse response = new ErrorResponse(ErrorType.ERROR, INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundError(NotFoundException exception){
        if(exception.getField() != null){
            StructuredErrorResponse response = new StructuredErrorResponse(ErrorType.STRUCTURED_ERROR, new HashMap<>());
            response.getErrors().put(exception.getField(), exception.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        ErrorResponse response = new ErrorResponse(ErrorType.ERROR, exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(VersionsMatchException.class)
    public ResponseEntity<ErrorResponse> handleVersionsMathError(VersionsMatchException exception){
        ErrorResponse response = new ErrorResponse(ErrorType.ERROR, exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(IncorrectDataException.class)
    public ResponseEntity<ErrorResponse> handleIncorrectDataError(IncorrectDataException exception){
        ErrorResponse response = new ErrorResponse(ErrorType.ERROR, exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
