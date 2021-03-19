package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuctionDto {
    private long id;

    private String title;

    private String description;

    private String category;

    private BigDecimal minimumPrice;

    private BigDecimal buyNowPrice;

    private BigDecimal actualPrice;

    private String location;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startDateTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endDateTime;

    private String itemImageUrl;

    private boolean buyNowEnable;

    private boolean isFinished;

    private long userId;

    private String accountName;

}
