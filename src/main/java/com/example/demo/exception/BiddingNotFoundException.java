package com.example.demo.exception;

public class BiddingNotFoundException extends RuntimeException{

    public BiddingNotFoundException(String message) {
        super(message);
    }
}
