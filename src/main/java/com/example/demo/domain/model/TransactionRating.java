package com.example.demo.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
public class TransactionRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Auction auction;

    @ManyToOne
    private User user;

    @NotEmpty
    @Size(min = 5, max = 80)
    private String comment;

    private Rating rating;
}
