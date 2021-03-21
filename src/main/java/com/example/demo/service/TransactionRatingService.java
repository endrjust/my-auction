package com.example.demo.service;

import com.example.demo.domain.model.Auction;
import com.example.demo.domain.model.TransactionRating;
import com.example.demo.domain.repository.TransactionRatingRepository;
import com.example.demo.exception.TransactionRatingException;
import com.example.demo.model.TransactionRatingDto;
import com.example.demo.service.mappers.TransactionRatingMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionRatingService {
    private final TransactionRatingRepository transactionRatingRepository;
    private final TransactionRatingMapper transactionRatingMapper;
    private final AuctionService auctionService;
    private final UserService userService;

    public TransactionRatingService(TransactionRatingRepository transactionRatingRepository, TransactionRatingMapper transactionRatingMapper, AuctionService auctionService, UserService userService) {
        this.transactionRatingRepository = transactionRatingRepository;
        this.transactionRatingMapper = transactionRatingMapper;
        this.auctionService = auctionService;
        this.userService = userService;
    }

    public List<TransactionRatingDto> findAllByUser(String accountName) {
//        return transactionRatingRepository.findAllByAccountName(accountName).stream()
//                .map(transactionRatingMapper::map).collect(Collectors.toList());
        return null;
    }

    public TransactionRatingDto findByAuctionId(long auctionId) {
        TransactionRating transactionRating = transactionRatingRepository.findByAuctionId(auctionId);
        return transactionRatingMapper.map(transactionRating);
    }

    public TransactionRatingDto addTransactionRating(TransactionRatingDto transactionRatingDto, long auctionId) {
        if (transactionRatingRepository.existsByAuctionId(auctionId)) {
            throw new TransactionRatingException("Oceniłeś już tą transakcję!");
        }
        TransactionRating transactionRating = transactionRatingMapper.map(transactionRatingDto);
        Auction auctionById = auctionService.findAuctionById(auctionId);
        transactionRating.setAuction(auctionById);
        transactionRating.setUser(userService.findUserEntity());
        return transactionRatingMapper.map(transactionRatingRepository.save(transactionRating));
    }


}
