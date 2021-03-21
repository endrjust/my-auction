package com.example.demo.model;

import com.example.demo.domain.model.Rating;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransactionRatingDto {





    private String comment;

    private Rating rating;
}
