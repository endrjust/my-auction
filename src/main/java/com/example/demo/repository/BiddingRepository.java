package com.example.demo.repository;

import com.example.demo.domain.model.Bidding;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BiddingRepository extends JpaRepository<Bidding, Long> {
}
