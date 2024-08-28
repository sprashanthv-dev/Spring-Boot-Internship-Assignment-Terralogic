package com.sprashanthv.nodeanalysis.exceptions;

import org.springframework.dao.DataAccessException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.sprashanthv.nodeanalysis.response.ErrorResponse;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class.getName());

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        logger.log(Level.SEVERE, ex.getMessage());

        return ResponseEntity.badRequest().body(
                new ErrorResponse(
                        HttpStatus.BAD_REQUEST.name(),
                        "Request body is missing"
                )
        );
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorResponse> handleDataAccessException(DataAccessException ex) {
        logger.log(Level.SEVERE, ex.getMessage());

        return ResponseEntity.internalServerError().body(
                new ErrorResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR.name(),
                        "Integrity constraints violated -- Trying to insert duplicate records"
                )
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleInvalidMethodArgumentsException(MethodArgumentNotValidException ex) {
        logger.log(Level.SEVERE, ex.getMessage());

        return ResponseEntity.badRequest().body(
                new ErrorResponse(
                        HttpStatus.BAD_REQUEST.name(),
                        "Request body empty (or) one or more of required fields is null"
                )
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleOtherExceptions(Exception ex) {

        logger.log(Level.SEVERE, ex.getMessage());

        return ResponseEntity.internalServerError().body(
                new ErrorResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR.name(),
                        "Unknown error occurred"
                )
        );
    }
}
