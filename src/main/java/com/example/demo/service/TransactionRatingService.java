package com.example.demo.service;

import com.example.demo.domain.model.TransactionRating;
import com.example.demo.domain.repository.TransactionRatingRepository;
import com.example.demo.exception.TransactionRatingException;
import com.example.demo.model.TransactionRatingDto;
import com.example.demo.service.mappers.TransactionRatingMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionRatingService {
    private final TransactionRatingRepository transactionRatingRepository;
    private final TransactionRatingMapper transactionRatingMapper;

    public TransactionRatingService(TransactionRatingRepository transactionRatingRepository, TransactionRatingMapper transactionRatingMapper) {
        this.transactionRatingRepository = transactionRatingRepository;
        this.transactionRatingMapper = transactionRatingMapper;
    }

    public List<TransactionRatingDto> findAllByUser(String accountName) {
        return transactionRatingRepository.findAllByAccountName(accountName).stream()
                .map(transactionRatingMapper::map).collect(Collectors.toList());
    }

    public TransactionRatingDto findByAuctionId(long auctionId) {
        TransactionRating transactionRating = transactionRatingRepository.findByAuctionId(auctionId);
        return transactionRatingMapper.map(transactionRating);
    }

    public TransactionRatingDto addTransactionRating(TransactionRatingDto transactionRatingDto) {
        if (transactionRatingRepository.existsByAuctionId(transactionRatingDto.getAuctionId())) {
            throw new TransactionRatingException("Oceniłeś już tą transakcję!");
        }
        TransactionRating transactionRating = transactionRatingMapper.map(transactionRatingDto);
        return transactionRatingMapper.map(transactionRatingRepository.save(transactionRating));
    }


}
