package com.example.demo.service.mappers;

import com.example.demo.domain.model.Auction;
import com.example.demo.domain.model.Bidding;
import com.example.demo.domain.model.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.BiddingDto;
import com.example.demo.service.AuctionService;
import com.example.demo.service.UserService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Component
public class BiddingMapper {

    UserService userService;
    UserMapper userMapper;
    AuctionService auctionService;
    AuctionMapper auctionMapper;

    @Autowired
    public BiddingMapper(UserService userService, UserMapper userMapper, AuctionService auctionService, AuctionMapper auctionMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.auctionService = auctionService;
        this.auctionMapper = auctionMapper;
    }

    public Bidding map(BiddingDto biddingDto) {
        Bidding bidding = new Bidding();
//        bidding.setAuction(auctionMapper.map(auctionService.findAll().stream().findFirst().orElseThrow()));
//        bidding.setUser(userMapper.map(userService.findUser(biddingDto.getUserId())));
        bidding.setOfferPrice(biddingDto.getOfferPrice());
        bidding.setOfferDateTime(LocalDateTime.now());
        return bidding;
    }

    public BiddingDto map(Bidding bidding) {
        BiddingDto biddingDto = new BiddingDto();
        biddingDto.setOfferPrice(bidding.getOfferPrice());
        biddingDto.setOfferDateTime(bidding.getOfferDateTime());
        biddingDto.setUserId(bidding.getUser().getId());
        biddingDto.setAuctionId(bidding.getAuction().getId());
        return biddingDto;
    }

}
