package com.example.estoque.exception;

import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> tratarValidacao(MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult().getFieldErrors().stream()
                .map(this::formatarErro)
                .toList();

        return resposta(HttpStatus.BAD_REQUEST, "Dados inválidos", errors);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> tratarNaoEncontrado(ResourceNotFoundException exception) {
        return resposta(HttpStatus.NOT_FOUND, exception.getMessage(), List.of());
    }

    private String formatarErro(FieldError error) {
        return error.getField() + ": " + error.getDefaultMessage();
    }

    private ResponseEntity<Map<String, Object>> resposta(HttpStatus status, String message, List<String> errors) {
        Map<String, Object> body = Map.of(
                "status", status.value(),
                "message", message,
                "errors", errors
        );
        return ResponseEntity.status(status).body(body);
    }
}
