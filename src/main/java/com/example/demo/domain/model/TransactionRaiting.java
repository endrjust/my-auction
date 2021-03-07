package com.example.demo.domain.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class TransactionRaiting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private Auction auction;
    @NotEmpty
    @Size(min = 5, max = 80)
    private String comment;
    @NotEmpty
    @Column(length = 1)

    private int rate;   //od 1 do 5
}
