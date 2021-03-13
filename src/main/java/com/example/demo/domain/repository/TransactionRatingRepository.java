package com.example.demo.domain.repository;

import com.example.demo.domain.model.TransactionRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRatingRepository extends JpaRepository<TransactionRating, Long> {

    List<TransactionRating> findAllByAccountName(String accountName);

    TransactionRating findByAuctionId(long auctionId);

    boolean existsByAuctionId(long auctionId);
}
