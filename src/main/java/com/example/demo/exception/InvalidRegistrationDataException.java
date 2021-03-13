package com.example.demo.exception;

public class InvalidRegistrationDataException extends Exception{
    public InvalidRegistrationDataException(String message) {
        super(message);
    }
}
