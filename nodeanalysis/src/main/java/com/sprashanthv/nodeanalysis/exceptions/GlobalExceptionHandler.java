package com.sprashanthv.nodeanalysis.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.sprashanthv.nodeanalysis.response.ApiErrorResponse;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class.getName());

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        logger.log(Level.SEVERE, ex.getMessage());

        return ResponseEntity.badRequest().body(
                new ApiErrorResponse(
                        HttpStatus.BAD_REQUEST.name(),
                        "Request body is missing"
                )
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidMethodArgumentsException(MethodArgumentNotValidException ex) {
        logger.log(Level.SEVERE, ex.getMessage());

        return ResponseEntity.badRequest().body(
                new ApiErrorResponse(
                        HttpStatus.BAD_REQUEST.name(),
                        "Request body empty (or) one or more of required fields is null"
                )
        );
    }
}
