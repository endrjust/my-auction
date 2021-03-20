package com.example.demo.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
public class TransactionRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long auctionId;
    private String title;
    private long userId;
    private long accountName;
    @NotEmpty
    @Size(min = 5, max = 80)
    private String comment;

    private Rate rate;
}
