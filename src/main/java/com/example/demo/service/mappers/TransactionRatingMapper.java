package com.example.demo.service.mappers;

import com.example.demo.domain.model.TransactionRating;
import com.example.demo.model.TransactionRatingDto;
import org.springframework.stereotype.Component;

@Component
public class TransactionRatingMapper {

    public TransactionRatingDto map(TransactionRating transactionRating) {
        TransactionRatingDto transactionRatingDto = new TransactionRatingDto();

        transactionRatingDto.setComment(transactionRating.getComment());
        transactionRatingDto.setRating(transactionRating.getRating());
        return transactionRatingDto;
    }

    public TransactionRating map(TransactionRatingDto transactionRatingDto) {
        TransactionRating transactionRating = new TransactionRating();

        transactionRating.setComment(transactionRatingDto.getComment());
        transactionRating.setRating(transactionRatingDto.getRating());
        return transactionRating;
    }
}
