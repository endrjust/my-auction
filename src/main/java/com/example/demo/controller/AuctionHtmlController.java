package com.example.demo.controller;

import com.example.demo.domain.model.Auction;
import com.example.demo.domain.model.Bidding;
import com.example.demo.domain.model.Category;
import com.example.demo.exception.TooLowPriceException;
import com.example.demo.model.AuctionDto;
import com.example.demo.model.BiddingDto;
import com.example.demo.service.AuctionService;
import com.example.demo.service.BiddingService;
import com.example.demo.service.TransactionRatingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AuctionHtmlController {

    AuctionService auctionService;
    BiddingService biddingService;
    private final TransactionRatingService transactionRatingService;

    public AuctionHtmlController(AuctionService auctionService, BiddingService biddingService, TransactionRatingService transactionRatingService) {
        this.auctionService = auctionService;
        this.biddingService = biddingService;
        this.transactionRatingService = transactionRatingService;
    }

    @GetMapping
    public String allAuctions(Model model) {
        List<Auction> auctionsList = auctionService.findAllEntities();
        model.addAttribute("auctions", auctionsList);
        return "/index";
    }


    @GetMapping("/auctionDetail/{auctionId}")
    public String auctionDetails(Model model, @PathVariable long auctionId,
                                 @RequestParam(required = false) boolean offerBidTooLow) {
        Auction auctionById = auctionService.findAuctionById(auctionId);
        Iterable<Bidding> allBids = biddingService.findBidByAuctionId(auctionId);
        if (offerBidTooLow) {
            model.addAttribute("tooLowPrice", true);
        }

        model.addAttribute("auction", auctionById);
        model.addAttribute("newBid", new BiddingDto());
        model.addAttribute("auctionId", auctionById.getId());
        model.addAttribute("bids", allBids);
        model.addAttribute("sellerReviews",transactionRatingService.findAllByAuction(auctionId));
        return "/auctionDetail";
    }

    @GetMapping("/auctionForm")
    public String getAuctionForm(Model model) {
        model.addAttribute("newAuction", new AuctionDto());
        return "/auctionForm";
    }

    @PostMapping("/addAuction")
    public String addAuctionPost(@Valid @ModelAttribute AuctionDto auctionDto) {
        auctionService.saveAuction(auctionDto);
        return "redirect:/auctionForm";
    }

    @PostMapping("/makeBid/{auctionId}")
    public String makeBid(@PathVariable long auctionId, @Valid @ModelAttribute BiddingDto biddingDto) {
        try {
            biddingDto.setAuctionId(auctionId);
            biddingService.makeBid(biddingDto);
            return "redirect:/auctionDetail/" + auctionId;
        } catch (TooLowPriceException e) {
            return "redirect:/auctionDetail/" + auctionId + "?offerBidTooLow=true";
        }
    }

    @GetMapping("/category/{categoryName}")
    public String findByCategory(@PathVariable("categoryName") Category categoryName, Model model) {
        List<Auction> allByCategory = auctionService.findAllByCategoryEntities(categoryName);
        model.addAttribute("auctions", allByCategory);
        return "/index";
    }

    @PostMapping("/buyNow/{auctionId}")
    public String buyNow(@PathVariable long auctionId, Model model) {
        auctionService.buyNow(auctionId);
        model.addAttribute("auctionId", auctionId);
        return "redirect:/auctionDetail/" + auctionId;
    }

    @PostMapping("/close-outdated-auctions")
    public String closeAllFinishedAuctions() {
        auctionService.closeOutdatedAuctions();
        return "redirect:/";
    }

    @GetMapping("/finished-auctions")
    public String findAllFinishedAuctions(Model model) {
        List<Auction> allFinishedAuctions = auctionService.findAllFinishedAuctions();
        model.addAttribute("auctions", allFinishedAuctions);
        return "/index";
    }
}
