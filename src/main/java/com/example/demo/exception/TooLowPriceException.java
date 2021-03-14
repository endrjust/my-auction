package com.example.demo.exception;

public class TooLowPriceException extends Exception{
    public TooLowPriceException(String message) {
        super(message);
    }
}
