package com.knoldus.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class RestControllerExceptionHandler {
    
    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<ApiException> handleUserNotFoundException(
            UserNotFoundException userNotFoundException) {
        ApiException apiException = ApiException.builder()
                .status(404)
                .timestamp(LocalDateTime.now())
                .error(userNotFoundException.getMessage())
                .build();
        return ResponseEntity.status(404).body(apiException);
    }
    
    @ExceptionHandler(value = {UserAlreadyExistsException.class})
    public ResponseEntity<ApiException> handleUserAlreadyExistsException(
            UserAlreadyExistsException userAlreadyExistsException) {
        ApiException apiException = ApiException.builder()
                .status(409)
                .timestamp(LocalDateTime.now())
                .error(userAlreadyExistsException.getMessage())
                .build();
        return ResponseEntity.status(409).body(apiException);
    }
}
