package com.example.demo.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@Entity(name = "auctions")
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

    private BigDecimal actualPrice;

    private boolean isPromoted;

    private String location;    //z encji User wyciąga miasto i woj.

    private LocalDateTime startDateTime;    //data i czas w momencie tworzenia

    private LocalDateTime endDateTime;  //za 7 dni od 'startDateTime

    private int numberOfViews;

    private boolean isFinished;

    @ManyToOne
    private User user;
}
