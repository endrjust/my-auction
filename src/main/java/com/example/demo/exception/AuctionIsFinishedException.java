package com.example.demo.exception;

public class AuctionIsFinishedException extends RuntimeException{
    public AuctionIsFinishedException(String message) {
        super(message);
    }
}
