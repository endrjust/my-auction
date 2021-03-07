package com.example.demo.controller;

import com.example.demo.domain.model.Bidding;
import com.example.demo.model.BiddingDto;
import com.example.demo.service.BiddingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bidding")
public class BiddingController {

    private BiddingService biddingService;

    public BiddingController(BiddingService biddingService) {
        this.biddingService = biddingService;
    }

    @GetMapping
    public Iterable<BiddingDto> findAllBids(){
        return biddingService.findAllBids();
    }

    @GetMapping("/{userId}")
    public List<Bidding> findBidByUserId(@RequestParam long userId){
       return biddingService.findBidByUserId(userId);
    }

    @GetMapping("/{auctionId}")
    public List<Bidding> findBidByAuctionId(@RequestParam long auctionId){
        return biddingService.findBidByAuctionId(auctionId);
    }


    @PostMapping
    public void makeBid(@RequestBody BiddingDto biddingDto) {
        biddingService.makeBid(biddingDto);
    }

    @PutMapping
    public void updateBid(@RequestBody BiddingDto biddingDto, Long biddingId) {
        biddingService.updateBid(biddingDto,biddingId);
    }

    @DeleteMapping
    public void deleteById(Long biddingId) {
        biddingService.removeAuctionById(biddingId);
    }

}
