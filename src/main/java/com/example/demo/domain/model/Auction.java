package com.example.demo.domain.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Auction {
    @Id
    @GeneratedValue
    @Column(name = "auction_id")
    private long id;

    @NotNull
    @Size(min = 5, max = 50)
    private String title;

    @NotNull
    @Size(max = 1000)
    private String description;

    @Enumerated(value = EnumType.STRING)
    private Category category;

    @Min(value = 1)
    private BigDecimal minimumPrice;

    @Min(value = 1)
    private BigDecimal buyNowPrice;

    private BigDecimal actualPrice;

    private boolean isPromoted;

    @NotEmpty
    private String location;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startDateTime;    //data i czas w momencie tworzenia

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endDateTime;      //za 7 dni od 'startDateTime

    private int numberOfViews;

    private String itemImageUrl;

    private boolean buyNowEnable;

    private boolean isFinished;

    @ManyToOne
    private User user;

    @OneToMany
    private List<Bidding> biddingList;
}
