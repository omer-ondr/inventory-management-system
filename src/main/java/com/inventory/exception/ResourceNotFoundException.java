package com.inventory.exception;

// Bu sınıfı, veritabanında bir ID bulamadığımızda fırlatacağız.
public class ResourceNotFoundException extends RuntimeException {
    
    public ResourceNotFoundException(String message) {
        super(message);
    }
}