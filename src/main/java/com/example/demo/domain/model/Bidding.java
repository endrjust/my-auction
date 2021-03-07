package com.example.demo.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor

public class Bidding {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private Auction auction;

    @ManyToOne
    private User user;

    private BigDecimal offerPrice;

    private LocalDateTime offerDateTime;
}
