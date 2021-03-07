package com.example.demo.controller;

import com.example.demo.domain.model.Bidding;
import com.example.demo.model.BiddingDto;
import com.example.demo.service.BiddingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bidding/")
public class BiddingController {

    private BiddingService biddingService;

    public BiddingController(BiddingService biddingService) {
        this.biddingService = biddingService;
    }

    @GetMapping
    public Iterable<BiddingDto> findAllBids(){
        return biddingService.findAllBids();
    }

    @PostMapping
    public void makeBid(@RequestBody Bidding bidding) {
        biddingService.makeBid(bidding);
    }

    @DeleteMapping
    public void deleteById(Long biddingId) {
        biddingService.removeAuctionById(biddingId);
    }

}
