package com.example.demo.domain.repository;

import com.example.demo.domain.model.Auction;
import com.example.demo.domain.model.Category;
import com.example.demo.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface AuctionRepository extends JpaRepository<Auction, Long> {

//    List<Auction> findAll();

//    Optional<Auction> findById(long auctionId);

    List<Auction> findAllByCategory(Category category);

    List<Auction> findAllByUser(String accountName);

    List<Auction> findAllByTitle(String title);

    List<Auction> findAllByLocation(String location);

    List<Auction> findAllByActualPriceBetween(BigDecimal priceLowerBound, BigDecimal priceUpperBound);

//    Auction save(Auction auction);

//    void delete(Auction auction);
    }
