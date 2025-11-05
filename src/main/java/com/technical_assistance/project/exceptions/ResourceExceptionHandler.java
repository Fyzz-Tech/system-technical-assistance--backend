package com.technical_assistance.project.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.stream.Collectors;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                error,
                e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> handleValidation(MethodArgumentNotValidException e, HttpServletRequest request) {
        String error = "Invalid data";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        String message = e.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining(", "));

        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                error,
                message,
                request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleInvalidEnum(HttpMessageNotReadableException ex) {
        if (ex.getCause() instanceof InvalidFormatException) {
            return ResponseEntity.badRequest().body("Valor para Enum inválido ou vazio . Verifique os dados enviados");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Requisição inválida. Verifique os dados enviados.");
    }

    @ExceptionHandler(ExistingResourceException.class)
    public ResponseEntity<StandardError> existingResource(ExistingResourceException e, HttpServletRequest request) {
        String error = "Existing resource";
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                error,
                e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ExternalServiceException.class)
    public ResponseEntity<StandardError> externalService(ExternalServiceException e, HttpServletRequest request){
        String error = "External service";
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                error,
                e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
