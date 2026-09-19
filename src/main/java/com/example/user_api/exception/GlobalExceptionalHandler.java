package com.example.user_api.exception;


import com.example.user_api.model.UserResponse;
import com.example.user_api.parser.UserParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionalHandler {
    @Autowired
    private UserParser userParser;

    // handles @Valid failures
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<UserResponse> handleValidationErrors(
            MethodArgumentNotValidException exception) {

        List<FieldError> fieldErrors = exception
                .getBindingResult()
                .getFieldErrors();

        String errorMessage = "Invalid input";
        if (!fieldErrors.isEmpty()) {
            FieldError firstError = fieldErrors.get(0);
            errorMessage = firstError.getField()
                    + ": "
                    + firstError.getDefaultMessage();
        }

        return ResponseEntity
                .badRequest()
                .body(userParser.buildErrorResponse(errorMessage));
    }

    // handles resource not found
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<UserResponse> handleNotFound(
            ResourceNotFoundException exception) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(userParser.buildErrorResponse(
                        exception.getMessage()));
    }

    // handles any unexpected error
    @ExceptionHandler(Exception.class)
    public ResponseEntity<UserResponse> handleGenericError(
            Exception exception) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(userParser.buildErrorResponse(
                        "Something went wrong: "
                                + exception.getMessage()));
    }


}
