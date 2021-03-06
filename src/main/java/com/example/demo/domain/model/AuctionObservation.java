package com.example.demo.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class AuctionObservation {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private Auction auction;
    @ManyToOne
    private User user;
}
