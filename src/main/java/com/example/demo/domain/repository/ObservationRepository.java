package com.example.demo.domain.repository;

import com.example.demo.domain.model.Observation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObservationRepository extends JpaRepository<Observation, Long> {
    List<Observation> findAllByUserId(long userId);

    boolean existsByAuctionIdAndUserId(long auctionId, long userId);

    void deleteByAuctionIdAndUserId(long auctionId, long userId);
}
