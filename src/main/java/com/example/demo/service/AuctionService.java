package com.example.demo.service;

import com.example.demo.domain.model.Auction;
import com.example.demo.domain.model.Category;
import com.example.demo.domain.model.User;
import com.example.demo.domain.repository.AuctionRepository;
import com.example.demo.exception.AuctionException;
import com.example.demo.exception.AuctionIsFinishedException;
import com.example.demo.exception.AuctionNotFoundException;
import com.example.demo.model.AuctionDto;
import com.example.demo.service.mappers.AuctionMapper;
import org.springframework.security.core.context.SecurityContextHolder;
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
        return auctionRepository.findAll().stream()
                .filter(auction -> !auction.isFinished()).collect(Collectors.toList());
    }

    public void closeOutdatedAuctions() {
        List<Auction> auctionList = auctionRepository.findAllByEndDateTimeIsBefore(LocalDateTime.now());
        auctionList.forEach(auction -> auction.setFinished(true));
        auctionRepository.saveAll(auctionList);
    }

    public List<Auction> findAllFinishedAuctions() {
        return auctionRepository.findAll().stream()
                .filter(Auction::isFinished).collect(Collectors.toList());
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

    //wyszuk po id

    public Auction saveAuction(AuctionDto auctionDto) {
        Auction auction = auctionMapper.map(auctionDto);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        auction.setStartDateTime(auctionDto.getStartDateTime());
        auction.setEndDateTime(auctionDto.getStartDateTime().plusDays(7L));
        auction.setUser(user);
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
        auction.setBuyNowEnable(auctionDto.isBuyNowEnable());

        return auctionRepository.save(auction);
    }

    public Auction findAuctionById(long auctionId) {
        return auctionRepository.findById(auctionId).orElseThrow(() -> new AuctionNotFoundException("Auction Not Found"));
    }

    public void removeAuctionById(long auctionId) {
        auctionRepository.deleteById(auctionId);
    }

    public void buyNow(long auctionId) {
        Auction auction = auctionRepository.findById(auctionId)
                .orElseThrow(() -> new AuctionNotFoundException("No Auction with this Id found."));
        if (auction.isFinished()) {
            throw new AuctionIsFinishedException("Cannot buy this auction. Auction is finished");
        }
        if (auction.isBuyNowEnable()) {
            auction.setFinished(true);
            auctionRepository.save(auction);
        } else {
            throw new AuctionException("Cannot buy this auction. Auction is bidden");
        }

    }

}
