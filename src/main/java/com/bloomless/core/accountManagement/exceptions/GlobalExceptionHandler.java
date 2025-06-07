package com.bloomless.core.accountManagement.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameAlreadyExisting.class)
    public ResponseEntity<String> handleUsernameAlreadyExisting(UsernameAlreadyExisting ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(EmailAlreadyExisting.class)
    public ResponseEntity<String> handleEmailAlreadyExisting(EmailAlreadyExisting ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(PasswordNotMatching.class)
    public ResponseEntity<String> handlePasswordNotMatching(PasswordNotMatching ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(UsernameNotFound.class)
    public ResponseEntity<String> handleUsernameNotFound(UsernameNotFound ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(PasswordNotFound.class)
    public ResponseEntity<String> handlePasswordNotFound(PasswordNotFound ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
