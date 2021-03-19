package com.example.demo.controller;

import com.example.demo.domain.model.Auction;
import com.example.demo.domain.model.Category;
import com.example.demo.model.AuctionDto;
import com.example.demo.service.AuctionService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/auction")
public class AuctionController {

    private final AuctionService auctionService;

    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @GetMapping
    public List<Auction> findAll() {
        return auctionService.findAllEntities();
    }

    @GetMapping("/archive")
    public List<Auction> findAllFinished(){
        return auctionService.findAllFinishedEntities();
    }

    @GetMapping(path = "/category")
    public List<Auction> findAllByCategory(@RequestParam("category") String category) {
        Category parsed = Category.valueOf(category.toUpperCase());
        return auctionService.findAllByCategoryEntities(parsed);
    }

    @GetMapping(path = "/accountName")
    public List<AuctionDto> findAllByUser(@RequestParam("accountName") String accountName) {
        return auctionService.findAllByUser(accountName);
    }

    @GetMapping(path = "/title")
    public List<AuctionDto> findAllByTitle(@RequestParam("title") String title) {
        return auctionService.findAllByTitle(title);
    }

    @GetMapping(path = "/location")
    public List<AuctionDto> findAllByLocation(@RequestParam("location") String location) {
        return auctionService.findAllByLocation(location);
    }

    @GetMapping(path = "/price")
    public List<AuctionDto> findAllByActualPriceBetween(@RequestParam("price") BigDecimal priceLowerBound, BigDecimal priceUpperBound) {
        return auctionService.findAllByActualPriceBetween(priceLowerBound, priceUpperBound);
    }

    @PostMapping
    public Auction saveAuction(@Valid @RequestBody AuctionDto auctionDto) {
        return auctionService.saveAuction(auctionDto);
    }

    @PutMapping(path = "/{auctionId}")
    public Auction updateAuction(@Valid @RequestBody AuctionDto auctionDto, @PathVariable long auctionId) {
        return auctionService.updateAuctionById(auctionDto, auctionId);
    }

    @DeleteMapping(path = "/{auctionId}")
    public void deleteAuction(@PathVariable long auctionId) {
        auctionService.removeAuctionById(auctionId);
    }
}
