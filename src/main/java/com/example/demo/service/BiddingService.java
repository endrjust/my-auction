package com.example.demo.service;

import com.example.demo.domain.model.Bidding;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.BiddingDto;
import com.example.demo.domain.repository.BiddingRepository;
import com.example.demo.service.mappers.BiddingMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BiddingService {

    private final BiddingRepository biddingRepository;
    private final BiddingMapper biddingMapper;

    public BiddingService(BiddingRepository biddingRepository, BiddingMapper biddingMapper) {
        this.biddingRepository = biddingRepository;
        this.biddingMapper = biddingMapper;
    }


    public void makeBid(BiddingDto biddingDto) {
        Bidding bidding = biddingMapper.map(biddingDto);
        bidding.setOfferDateTime(LocalDateTime.now());
        biddingRepository.save(bidding);
    }

    public Iterable<BiddingDto> findAllBids() {
        return biddingRepository.findAll().stream().map(biddingMapper::map).collect(Collectors.toList());
    }

    public void removeAuctionById(long biddingId) {
        biddingRepository.deleteById(biddingId);
    }

    public void updateBid(BiddingDto biddingDto, Long biddingId) {
        Bidding bidding = biddingRepository.findById(biddingId)
                .orElseThrow(() -> new UserNotFoundException("Bidding with id " + biddingId + " not found"));
        bidding.setOfferPrice(biddingDto.getOfferPrice());
        bidding.setOfferDateTime(biddingDto.getOfferDateTime());
        bidding.setId(biddingDto.getUserId());
        biddingRepository.save(bidding);
    }

    public List<Bidding> findBidByUserId(long userId) {
        return biddingRepository.findAll().stream()
                .filter(bid -> bid.getUser().getId() == userId)
                .collect(Collectors.toList());
    }

    public List<Bidding> findBidByAuctionId(long auctionId) {
        return biddingRepository.findAll().stream()
                .filter(bid -> bid.getAuction().getId() == auctionId)
                .collect(Collectors.toList());
    }
}
