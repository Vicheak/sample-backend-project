package com.vicheak.springdatajpademoproject.exception;

import com.vicheak.springdatajpademoproject.base.BaseError;
import com.vicheak.springdatajpademoproject.base.FieldError;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ValidationException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationEx(MethodArgumentNotValidException ex) {
        List<FieldError> errors = new ArrayList<>();

        ex.getFieldErrors().forEach(fieldError -> {
            errors.add(new FieldError(fieldError.getField(),
                    fieldError.getDefaultMessage()));
        });

        var baseError = BaseError.builder()
                .message("Something went wrong!")
                .code(8888)
                .status(false)
                .localDateTime(LocalDateTime.now())
                .errors(errors)
                .build();
        return new ResponseEntity<>(baseError, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ResourceException.class)
    public Map<String, Object> handleResourceEx(ResourceException ex) {
        Map<String, Object> errorDto = new HashMap<>();

        errorDto.put("message", "Validation failed, please check your input");
        errorDto.put("code", 8888);
        errorDto.put("status", false);
        errorDto.put("timestamp", LocalDateTime.now());
        errorDto.put("errors", ex.getMessage());
        return errorDto;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({DataIntegrityViolationException.class, JpaObjectRetrievalFailureException.class})
    public Map<String, Object> handleSqlEx() {
        Map<String, Object> errorDto = new HashMap<>();

        errorDto.put("message", "Validation failed, please check your input");
        errorDto.put("code", 8888);
        errorDto.put("status", false);
        errorDto.put("timestamp", LocalDateTime.now());
        errorDto.put("errors", "Data violation exception, there might be due to data violation or constraints");
        return errorDto;
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> handleRequestParamEx(MissingServletRequestParameterException ex){
        var baseError = BaseError.builder()
                .message("Something went wrong!")
                .code(9999)
                .status(false)
                .localDateTime(LocalDateTime.now())
                .errors(ex.getMessage())
                .build();
        return new ResponseEntity<>(baseError, HttpStatus.BAD_REQUEST);
    }

}
