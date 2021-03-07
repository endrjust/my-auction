package com.example.demo.exception;

public class ObservationNotFoundException extends RuntimeException {
    public ObservationNotFoundException(String message) {
        super(message);
    }
}
