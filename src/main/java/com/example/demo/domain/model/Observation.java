package com.example.demo.domain.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class Observation {

    @Id
    @GeneratedValue
    private long id;

    private long auctionId;
    private long userId;
    private LocalDateTime startObservationDate;
}
