package com.example.exception;

import com.example.constants.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFound() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Constants.PRODUCT_NOT_FOUND_MESSAGE);
    }
}
