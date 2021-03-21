package com.example.demo.controller;

import com.example.demo.domain.model.Auction;
import com.example.demo.model.TransactionRatingDto;
import com.example.demo.service.AuctionService;
import com.example.demo.service.TransactionRatingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class TransactionRatingHtmlController {
    private final TransactionRatingService transactionRatingService;
    private final AuctionService auctionService;

    public TransactionRatingHtmlController(TransactionRatingService transactionRatingService, AuctionService auctionService) {
        this.transactionRatingService = transactionRatingService;
        this.auctionService = auctionService;
    }

    @GetMapping("/rateForm/{auctionId}")
    public String getRateForm(@PathVariable long auctionId, Model model) {
        model.addAttribute("newRate", new TransactionRatingDto());
        Auction auctionById = auctionService.findAuctionById(auctionId);
        model.addAttribute("auctionById", auctionById);
        return "/rateForm";
    }

    @PostMapping("/addRate/{auctionId}")
    public String addTransactionRate(@PathVariable long auctionId, @Valid @ModelAttribute TransactionRatingDto transactionRatingDto) {
        transactionRatingService.addTransactionRating(transactionRatingDto, auctionId);
        return "redirect:/auctionDetail/" + auctionId;
    }

}
