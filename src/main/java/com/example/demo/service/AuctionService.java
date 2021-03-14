package com.example.demo.service;

import com.example.demo.domain.model.Auction;
import com.example.demo.domain.model.Category;
import com.example.demo.domain.model.User;
import com.example.demo.domain.repository.AuctionRepository;
import com.example.demo.exception.AuctionNotFoundException;
import com.example.demo.model.AuctionDto;
import com.example.demo.service.mappers.AuctionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuctionService {

    private final AuctionRepository auctionRepository;
    private final AuctionMapper auctionMapper;

    public AuctionService(AuctionRepository auctionRepository, AuctionMapper auctionMapper) {
        this.auctionRepository = auctionRepository;
        this.auctionMapper = auctionMapper;
    }

    public List<Auction> findAllEntities() {
        return auctionRepository.findAll();
    }

    public List<AuctionDto> findAll() {
        return auctionRepository.findAll().stream()
                .map(auctionMapper::map).collect(Collectors.toList());
    }

    public List<AuctionDto> findAllByCategory(Category category) {
        return auctionRepository.findAllByCategory(category).stream()
                .map(auctionMapper::map).collect(Collectors.toList());

    }

    public List<Auction> findAllByCategoryEntities(Category category) {
        return auctionRepository.findAllByCategory(category);

    }


    public List<AuctionDto> findAllByUser(String accountName) {
        return auctionRepository.findAllByUser(accountName).stream()
                .map(auctionMapper::map).collect(Collectors.toList());
    }

    public List<AuctionDto> findAllByTitle(String title) {
        return auctionRepository.findAllByTitle(title).stream()
                .map(auctionMapper::map).collect(Collectors.toList());
    }

    public List<AuctionDto> findAllByLocation(String location) {
        return auctionRepository.findAllByLocation(location).stream()
                .map(auctionMapper::map).collect(Collectors.toList());
    }

    public List<AuctionDto> findAllByActualPriceBetween(BigDecimal priceLowerBound, BigDecimal priceUpperBound) {
        return auctionRepository.findAllByActualPriceBetween(priceLowerBound, priceUpperBound).stream()
                .map(auctionMapper::map).collect(Collectors.toList());
    }

    public Auction saveAuction(AuctionDto auctionDto) {
        Auction auction = auctionMapper.map(auctionDto);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //LocalDateTime now = LocalDateTime.now();
        auction.setStartDateTime(auctionDto.getStartDateTime());
        auction.setEndDateTime(auctionDto.getStartDateTime().plusDays(7L));
        auction.setUser(user);
        //todo auction.setUser(auctionDto.getAccountName());
        return auctionRepository.save(auction);
    }

    public Auction updateAuctionById(AuctionDto auctionDto, long auctionId) {
        Auction auction = auctionRepository.findById(auctionId)
                .orElseThrow(() -> new AuctionNotFoundException("No Auction with this Id found."));

        auction.setTitle(auctionDto.getTitle());
        auction.setDescription(auctionDto.getDescription());
        auction.setMinimumPrice(auctionDto.getMinimumPrice());
        auction.setActualPrice(auctionDto.getActualPrice());
        auction.setBuyNowPrice(auctionDto.getBuyNowPrice());
        return auctionRepository.save(auction);
    }

    public Auction findAuctionById(long auctionId) {
        return auctionRepository.findById(auctionId).orElseThrow(() -> new AuctionNotFoundException("Auction Not Found"));
    }

    public void removeAuctionById(long auctionId) {
        auctionRepository.deleteById(auctionId);
    }

}
