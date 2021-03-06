package com.example.demo.domain.model;

import javax.persistence.*;

@Entity
public class TransactionRaiting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private Auction auction;

    private String comment;

    private int rate;   //od 1 do 5
}
