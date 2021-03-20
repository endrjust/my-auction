package com.example.demo.domain.repository;

import com.example.demo.domain.model.Auction;
import com.example.demo.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface AuctionRepository extends JpaRepository<Auction, Long> {

    List<Auction> findAllByCategory(Category category);

    List<Auction> findAllByUser(String accountName);

    List<Auction> findAllByTitle(String title);

    List<Auction> findAllByLocation(String location);

    List<Auction> findAllByActualPriceBetween(BigDecimal priceLowerBound, BigDecimal priceUpperBound);

    List<Auction> findAllByEndDateTimeBefore(LocalDateTime dateTime);
}
