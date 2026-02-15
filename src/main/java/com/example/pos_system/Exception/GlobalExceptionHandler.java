package com.example.pos_system.Exception;

import com.example.pos_system.util.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGenericException(Exception e){
        return new ResponseEntity<>(new ApiResponse<>(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                e.getMessage()
        ), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ApiResponse<String>> handelNullPointException(NullPointerException e){
        return new ResponseEntity<>(new ApiResponse<>(
                HttpStatus.BAD_REQUEST.value(),
                "Null values are not allowed",
                e.getMessage()
        ), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {errors.put(error.getObjectName(), error.getDefaultMessage());});
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Validation failed", errors),HttpStatus.BAD_REQUEST);
    }
}
