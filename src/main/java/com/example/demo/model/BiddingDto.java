package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class BiddingDto {


    private long auctionId;

    private long userId;

    private BigDecimal offerPrice;

    private LocalDateTime offerDateTime;
}
