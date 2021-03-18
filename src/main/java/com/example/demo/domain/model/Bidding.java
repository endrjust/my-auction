package com.example.demo.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor

public class Bidding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Auction auction;

    @ManyToOne
    private User user;

    private BigDecimal offerPrice;

    private LocalDateTime offerDateTime;
}
