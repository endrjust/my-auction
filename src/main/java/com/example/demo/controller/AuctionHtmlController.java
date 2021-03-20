package com.example.demo.controller;

import com.example.demo.domain.model.Auction;
import com.example.demo.domain.model.Bidding;
import com.example.demo.domain.model.Category;
import com.example.demo.exception.TooLowPriceException;
import com.example.demo.model.AuctionDto;
import com.example.demo.model.BiddingDto;
import com.example.demo.service.AuctionService;
import com.example.demo.service.BiddingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AuctionHtmlController {

    AuctionService auctionService;
    BiddingService biddingService;

    public AuctionHtmlController(AuctionService auctionService, BiddingService biddingService) {
        this.auctionService = auctionService;
        this.biddingService = biddingService;
    }

    @GetMapping
    public String allAuctions(Model model) {
        List<Auction> auctionsList = auctionService.findAllEntities();
        model.addAttribute("auctions", auctionsList);
        return "index";
    }


    @GetMapping("/auctionDetail/{auctionId}")
    public String auctionDetails(Model model, @PathVariable long auctionId, @RequestParam(required = false) boolean offerBidTooLow) {
        Auction auctionById = auctionService.findAuctionById(auctionId);
        Iterable<Bidding> allBids = biddingService.findAllBidsEntity();
        if (offerBidTooLow) {
            model.addAttribute("tooLowPrice", true);
        }
        model.addAttribute("auction", auctionById);
        model.addAttribute("newBid", new BiddingDto());
        model.addAttribute("auctionId", auctionById.getId());
        model.addAttribute("bids", allBids);
        return "auctionDetail";
    }

    @GetMapping("/auctionForm")
    public String addAuctionForm(Model model) {
        model.addAttribute("newAuction", new AuctionDto());
        return "auctionForm";
    }

    @PostMapping("/addAuction")
    public String addAuctionPost(@ModelAttribute AuctionDto auctionDto) {
        auctionService.saveAuction(auctionDto);
        return "redirect:auctionForm";
    }

    @PostMapping("/makeBid/{auctionId}")
    public String makeBid(@PathVariable long auctionId, @ModelAttribute BiddingDto biddingDto, Model model) {
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
        return "index";
    }

    @PostMapping("/buyNow/{auctionId}")
    public String buyNow(@PathVariable long auctionId, Model model) {
        auctionService.buyNow(auctionId);
        model.addAttribute("auctionId", auctionId);
        return "redirect:/auctionDetail/" + auctionId;
    }


    @PostMapping("/close-outdated-auctions")
    public String closeFinishedAuctions(){
    auctionService.closeOutdatedAuctions();
        return "redirect:/";
    }

    @GetMapping("/finished-auctions")
    public String findAllFinishedAuctions(Model model){
        List<Auction> allFinishedAuctions = auctionService.findAllFinishedAuctions();
        model.addAttribute("auctions",allFinishedAuctions);
        return "index";
    }
}
