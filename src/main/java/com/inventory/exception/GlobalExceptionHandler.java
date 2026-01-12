package com.inventory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice // Tüm Controller'ları dinleyen bir "Dinleyici" olduğunu belirtir.
public class GlobalExceptionHandler {

    // 1. SENARYO: Veri Bulunamadı (404)
    // ResourceNotFoundException fırlatıldığında burası çalışır.
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        
        ErrorResponse error = new ErrorResponse(
            HttpStatus.NOT_FOUND.value(), 
            "Veri Bulunamadı", 
            ex.getMessage()
        );
        
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // 3. SENARYO: Validation Hatası (400 Bad Request)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        
        // Hangi alanlarda hata var? (Örn: price: "Fiyat negatif olamaz")
        Map<String, String> errors = new HashMap<>();
        
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage())
        );

        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Doğrulama Hatası",
            errors.toString() // Hataları string olarak basıyoruz
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // 2. SENARYO: Genel Hatalar (500)
    // Kodda öngörmediğimiz başka bir hata olursa (NullPointer vb.) burası yakalar.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
        
        ErrorResponse error = new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(), 
            "Sunucu Hatası", 
            "Beklenmedik bir hata oluştu: " + ex.getMessage()
        );
        
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}