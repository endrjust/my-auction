package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ObservationDto {
    private long auctionId;
    private long userId;

    public ObservationDto(long auctionId, long userId) {
        this.auctionId = auctionId;
        this.userId = userId;
    }
}
