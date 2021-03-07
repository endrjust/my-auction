package com.example.demo.exception;

public class TransactionRatingException extends RuntimeException {
    public TransactionRatingException(String message) {
        super(message);
    }
}
