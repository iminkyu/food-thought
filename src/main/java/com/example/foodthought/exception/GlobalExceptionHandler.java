package com.example.foodthought.exception;

import com.example.foodthought.common.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors()
                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
        return ResponseEntity.badRequest().body(new ResponseDto(HttpStatus.BAD_REQUEST.value(), errors.toString()));
    }

    @ExceptionHandler({IllegalArgumentException.class, AuthenticationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity handleIllegalArgumentExceptions(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(new ResponseDto(HttpStatus.BAD_REQUEST.value(),ex.getMessage()));
    }
}