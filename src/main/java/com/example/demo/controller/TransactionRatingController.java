package com.example.demo.controller;

import com.example.demo.model.TransactionRatingDto;
import com.example.demo.service.TransactionRatingService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/transaction-rate")
public class TransactionRatingController {
    private final TransactionRatingService transactionRatingService;

    public TransactionRatingController(TransactionRatingService transactionRatingService) {
        this.transactionRatingService = transactionRatingService;
    }

    @PostMapping
    public TransactionRatingDto addTransactionRate(@Valid @RequestBody TransactionRatingDto transactionRatingDto) {
        return transactionRatingService.addTransactionRating(transactionRatingDto, 1);//naprawić
    }

    @GetMapping(path = "/{userId}")
    public List<TransactionRatingDto> findAllByUser(@RequestParam("userId") long userId) {
        return transactionRatingService.findAllByUser(userId);
    }

    @GetMapping(path = "/{auctionId}")
    public TransactionRatingDto findByAuctionId(@PathVariable long auctionId) {
        return transactionRatingService.findByAuctionId(auctionId);
    }
}
