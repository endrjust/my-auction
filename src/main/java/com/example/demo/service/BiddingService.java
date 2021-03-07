package com.example.demo.service;

import com.example.demo.domain.model.Bidding;
import com.example.demo.model.BiddingDto;
import com.example.demo.repository.BiddingRepository;
import com.example.demo.service.mappers.BiddingMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
public class BiddingService {

    private final BiddingRepository biddingRepository;
    private final BiddingMapper biddingMapper;

    public BiddingService(BiddingRepository biddingRepository, BiddingMapper biddingMapper) {
        this.biddingRepository = biddingRepository;
        this.biddingMapper = biddingMapper;
    }


    public void makeBid(Bidding bidding) {
        bidding.setOfferDateTime(LocalDateTime.now());
        biddingRepository.save(bidding);
    }

    public Iterable<BiddingDto> findAllBids() {
        return biddingRepository.findAll().stream().map(biddingMapper::map).collect(Collectors.toList());
    }

    public void removeAuctionById(long biddingId) {
        biddingRepository.deleteById(biddingId);
    }

}
