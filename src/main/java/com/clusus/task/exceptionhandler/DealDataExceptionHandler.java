package com.clusus.task.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DealDataExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex) {

        BindingResult bindingResult = ex.getBindingResult();
        String errorMessage = "Invalid request body: ";
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMessage += fieldError.getField() + " - " + fieldError.getDefaultMessage() + ", ";
        }
        return ResponseEntity.badRequest().body(errorMessage);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleParentException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

}
