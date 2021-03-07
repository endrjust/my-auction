package com.example.demo.controller;

import com.example.demo.domain.model.Auction;
import com.example.demo.domain.model.Category;
import com.example.demo.model.AuctionDto;
import com.example.demo.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AuctionController {

    private final AuctionService auctionService;

    @Autowired
    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @GetMapping(path = "/auctions")
    public List<AuctionDto> findAll() {
        return auctionService.findAll();
    }

    @GetMapping(path = "/{category}")
    public List<AuctionDto> findAllByCategory(Category category) {
        return auctionService.findAllByCategory(category);
    }

    @GetMapping(path = "/{accountName}")
    public List<AuctionDto> findAllByUser(String accountName) {
        return auctionService.findAllByUser(accountName);
    }

    @GetMapping(path = "/{title}")
    public List<AuctionDto> findAllByTitle(String title) {
        return auctionService.findAllByTitle(title);
    }

    @GetMapping(path = "/{location}")
    public List<AuctionDto> findAllByLocation(String location) {
        return auctionService.findAllByLocation(location);
    }

    @GetMapping(path = "/{price}")
    public List<AuctionDto> findAllByActualPriceBetween(BigDecimal priceLowerBound, BigDecimal priceUpperBound) {
        return auctionService.findAllByActualPriceBetween(priceLowerBound, priceUpperBound);
    }

    @GetMapping(path = "/{auctionId}")
    public Auction saveAuction(AuctionDto auctionDto) {
        return auctionService.saveAuction(auctionDto);
    }

    @GetMapping(path = "/{auctionId}")
    public Auction updateAuction(AuctionDto auctionDto, long auctionId) {
        return auctionService.updateAuctionById(auctionDto, auctionId);
    }

    @GetMapping(path = "/{auctionId}")
    public void deleteAuction(long auctionId) {
        auctionService.removeAuctionById(auctionId);
    }
}
