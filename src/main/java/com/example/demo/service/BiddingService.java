package com.example.demo.service;

import com.example.demo.domain.model.Auction;
import com.example.demo.domain.model.Bidding;
import com.example.demo.domain.model.User;
import com.example.demo.exception.AuctionException;
import com.example.demo.exception.TooLowPriceException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.AuctionDto;
import com.example.demo.model.BiddingDto;
import com.example.demo.domain.repository.BiddingRepository;
import com.example.demo.service.mappers.AuctionMapper;
import com.example.demo.service.mappers.BiddingMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BiddingService {

    private final BiddingRepository biddingRepository;
    private final BiddingMapper biddingMapper;
    private final UserService userService;
    private final AuctionService auctionService;
    private final AuctionMapper auctionMapper;

    public BiddingService(BiddingRepository biddingRepository, BiddingMapper biddingMapper, UserService userService, AuctionService auctionService, AuctionMapper auctionMapper) {
        this.biddingRepository = biddingRepository;
        this.biddingMapper = biddingMapper;
        this.userService = userService;
        this.auctionService = auctionService;
        this.auctionMapper = auctionMapper;
    }


    public void makeBid(BiddingDto biddingDto) throws TooLowPriceException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AuctionDto auctionDto =auctionMapper.map( auctionService.findAuctionById(biddingDto.getAuctionId()));

        if(biddingDto.getOfferPrice()==null){
            throw new AuctionException("Offered price cannot be empty.");
        }
        if (biddingDto.getOfferPrice().compareTo(auctionDto.getActualPrice()) <= 0 ){
            throw new TooLowPriceException("Offered rice has to be higher than actual price.");
        }
        auctionDto.setActualPrice(biddingDto.getOfferPrice());
        auctionDto.setBuyNowEnable(false);
        Bidding bidding = biddingMapper.map(biddingDto);
        bidding.setUser(user);
        bidding.setAuction(auctionService.findAuctionById(biddingDto.getAuctionId()));
        bidding.setOfferDateTime(LocalDateTime.now());


       auctionService.updateAuctionById(auctionDto, biddingDto.getAuctionId());
        biddingRepository.save(bidding);
    }

    public Iterable<BiddingDto> findAllBids() {
        return biddingRepository.findAll().stream().map(biddingMapper::map).collect(Collectors.toList());
    }

    public Iterable<Bidding> findAllBidsEntity(){
        return biddingRepository.findAll();
    }

    public void removeBidById(long biddingId) {
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
