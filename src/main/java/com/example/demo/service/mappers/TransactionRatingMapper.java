package com.example.demo.service.mappers;

import com.example.demo.domain.model.TransactionRating;
import com.example.demo.model.TransactionRatingDto;
import org.springframework.stereotype.Component;

@Component
public class TransactionRatingMapper {

    public TransactionRatingDto map(TransactionRating transactionRating) {
        TransactionRatingDto transactionRatingDto = new TransactionRatingDto();
        transactionRatingDto.setAuctionId(transactionRating.getAuctionId());
        transactionRatingDto.setTitle(transactionRating.getTitle());
        transactionRatingDto.setAccountName(transactionRating.getAccountName());
        transactionRatingDto.setComment(transactionRating.getComment());
//        transactionRatingDto.setRate(transactionRating.getRate());
        return transactionRatingDto;
    }

    public TransactionRating map(TransactionRatingDto transactionRatingDto) {
        TransactionRating transactionRating = new TransactionRating();
        transactionRating.setAuctionId(transactionRatingDto.getAuctionId());
        transactionRating.setTitle(transactionRatingDto.getTitle());
        transactionRating.setAccountName(transactionRatingDto.getAccountName());
        transactionRating.setComment(transactionRatingDto.getComment());
//        transactionRating.setRate(transactionRatingDto.getRate());
        return transactionRating;
    }
}
