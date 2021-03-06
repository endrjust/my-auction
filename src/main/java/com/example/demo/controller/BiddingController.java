package com.example.demo.controller;

import com.example.demo.domain.model.Bidding;
import com.example.demo.exception.TooLowPriceException;
import com.example.demo.model.BiddingDto;
import com.example.demo.service.BiddingService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/bidding")
public class BiddingController {

    private final BiddingService biddingService;

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
    public void makeBid(@Valid @RequestBody BiddingDto biddingDto) throws TooLowPriceException {
        biddingService.makeBid(biddingDto);
    }

    @PutMapping
    public void updateBid(@Valid @RequestBody BiddingDto biddingDto, Long biddingId) {
        biddingService.updateBid(biddingDto,biddingId);
    }

    @DeleteMapping
    public void deleteById(Long biddingId) {
        biddingService.removeBidById(biddingId);
    }

}
