package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransactionRatingDto {
    private long auctionId;
    private String title;
    private long accountName;
    private String comment;
    private int rate;
}
