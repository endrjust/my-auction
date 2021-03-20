package com.example.demo.service.mappers;

import com.example.demo.domain.model.Auction;
import com.example.demo.domain.model.Category;
import com.example.demo.model.AuctionDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuctionMapper {
    public Auction map(AuctionDto auctionDto) {
        Auction auction = new Auction();
        auction.setTitle(auctionDto.getTitle());
        auction.setDescription(auctionDto.getDescription());
        auction.setCategory(Category.valueOf(auctionDto.getCategory().toUpperCase()));
        auction.setMinimumPrice(auctionDto.getMinimumPrice());
        auction.setBuyNowPrice(auctionDto.getBuyNowPrice());
        auction.setActualPrice(auctionDto.getActualPrice());
        auction.setPromoted(auctionDto.isPromoted());
        auction.setLocation(auctionDto.getLocation());
        auction.setBuyNowEnable(auctionDto.isBuyNowEnable());
        auction.setItemImageUrl(auctionDto.getItemImageUrl());
        return auction;
    }

    public AuctionDto map(Auction auction) {
        AuctionDto auctionDto = new AuctionDto();
        auctionDto.setId(auction.getId());
        auctionDto.setTitle(auction.getTitle());
        auctionDto.setDescription(auction.getDescription());
        auctionDto.setCategory(auction.getCategory().toString());
        auctionDto.setBuyNowPrice(auction.getBuyNowPrice());
        auctionDto.setActualPrice(auction.getActualPrice());
        auctionDto.setLocation(auction.getLocation());
        auctionDto.setBuyNowEnable(auction.isBuyNowEnable());
        if (auction.getUser() != null) {
            auctionDto.setAccountName(auction.getUser().getAccountName());
        }
        auctionDto.setItemImageUrl(auction.getItemImageUrl());
        return auctionDto;
    }


}
