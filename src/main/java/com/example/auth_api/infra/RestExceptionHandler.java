package com.example.auth_api.infra;

import com.example.auth_api.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    private ResponseEntity<RestErrorMessage> productNotFoundHandler(ProductNotFoundException exception){
        RestErrorMessage threatResponse = new RestErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage(),
                System.currentTimeMillis(),
                "Produto não encontrado"
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatResponse);
    }

    // Exemplo de fallback para outros erros não tratados
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.put("error", "Erro interno no servidor");
        error.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
