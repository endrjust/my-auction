package com.example.demo.model;

import com.example.demo.domain.model.Category;
import com.example.demo.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private String location;    //z encji User wyciąga miasto i woj.

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private long userId;

    private String accountName;
}
