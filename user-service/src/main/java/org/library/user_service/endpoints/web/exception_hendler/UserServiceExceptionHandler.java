package org.library.user_service.endpoints.web.exception_hendler;

import jakarta.validation.ConstraintViolationException;
import org.library.base_package.enums.ErrorType;
import org.library.base_package.errors.ErrorResponse;
import org.library.base_package.errors.StructuredErrorResponse;
import org.library.user_service.core.exception.EmailAlreadyTakenException;
import org.library.user_service.core.exception.LoginException;
import org.library.user_service.core.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
    @ExceptionHandler({RuntimeException.class, Error.class})
    public ResponseEntity<ErrorResponse> handleInnerError(){
        ErrorResponse response = new ErrorResponse(ErrorType.ERROR, INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(){
        ErrorResponse response = new ErrorResponse(ErrorType.ERROR, NOT_READABLE);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(EmailAlreadyTakenException.class)
    public ResponseEntity<StructuredErrorResponse> handleEmailTakenError(EmailAlreadyTakenException exception){
        StructuredErrorResponse response = new StructuredErrorResponse(ErrorType.STRUCTURED_ERROR, new HashMap<>());
        response.getErrors().put(exception.getField(), exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<ErrorResponse> handleLoginError(LoginException exception){
        ErrorResponse response = new ErrorResponse(ErrorType.ERROR, exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
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
}
