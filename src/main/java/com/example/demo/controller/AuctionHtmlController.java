package com.example.demo.controller;

import com.example.demo.domain.model.Auction;
import com.example.demo.domain.model.Category;
import com.example.demo.exception.AuctionNotFoundException;
import com.example.demo.model.AuctionDto;
import com.example.demo.model.BiddingDto;
import com.example.demo.service.AuctionService;
import com.example.demo.service.BiddingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping("/auction")
public class AuctionHtmlController {

    AuctionService auctionService;
    BiddingService biddingService;

    public AuctionHtmlController(AuctionService auctionService, BiddingService biddingService) {
        this.auctionService = auctionService;
        this.biddingService = biddingService;
    }

    @GetMapping("/auctions")
    public String allAuctions(Model model) {
        List<Auction> auctionsList = auctionService.findAllEntities();
        model.addAttribute("auctionss", auctionsList);
        return "index";
    }

    @GetMapping("/auctionDetail/{auctionId}")
    public String auctionDetails(Model model, @PathVariable long auctionId) {
        Auction auctionById = auctionService.findAuctionById(auctionId);
        Iterable<BiddingDto> allBids = biddingService.findAllBids();
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
    public String makeBid(@PathVariable long auctionId, @ModelAttribute BiddingDto biddingDto) {
        biddingDto.setAuctionId(auctionId);
        biddingService.makeBid(biddingDto);
        return "redirect:/auctionDetail/" + auctionId;
    }

    @GetMapping("/category/{categoryName}")
    public String findByCategory(@PathVariable("categoryName") Category categoryName, Model model) {
        List<Auction> allByCategory = auctionService.findAllByCategoryEntities(categoryName);
        model.addAttribute("auctionss", allByCategory);
        return "index";
    }

}
