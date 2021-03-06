package com.example.demo.domain.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Auction {

    @Id
    @GeneratedValue
    private long id;

    private String title;

    private String description;
    @Enumerated(value = EnumType.STRING)
    private Category category;

    private BigDecimal minimumPrice;

    private BigDecimal buyNowPrice;

    private boolean isPromoted;

    private String location;    //z encji User wyciąga miasto i woj.

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private int numberOfViews;

    private boolean isFinished;

    @ManyToOne
    private User user;
}
