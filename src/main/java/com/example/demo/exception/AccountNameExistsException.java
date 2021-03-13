package com.example.demo.exception;

public class AccountNameExistsException extends Exception {
    public AccountNameExistsException(String message) {
        super(message);
    }
}
