package com.example.demo.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Bidding {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private Auction auction;

    @ManyToOne
    private User user;
    @NotEmpty
    private BigDecimal offerPrice;

    private LocalDateTime offerDateTime;
}