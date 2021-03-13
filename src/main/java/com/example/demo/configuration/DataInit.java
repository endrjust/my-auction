package com.example.demo.configuration;

import com.example.demo.domain.model.*;
import com.example.demo.domain.repository.AuctionRepository;
import com.example.demo.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Service
public class DataInit {
   private final AuctionRepository auctionRepository;
   private final UserRepository userRepository;

    public DataInit(AuctionRepository auctionRepository, UserRepository userRepository) {
        this.auctionRepository = auctionRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init(){
        User user = new User();
        user.setAccountName("lukasz123");
        user.setPassword("pass");
        user.setCity("torun");
        user.setEmail("lukasz123@gmail.com");
        user.setStreet("XXX");
        user.setAccountStatus(AccountStatus.ACTIVE);
        user.setAccountType(AccountType.PREMIUM);
        user = userRepository.save(user);
        Auction auction = new Auction();
        auction.setTitle("aukcja1");
        auction.setDescription("opis aukcji1");
        auction.setBuyNowPrice(new BigDecimal(200));
        auction.setActualPrice(new BigDecimal(160));
        auction.setCategory(Category.HOME);
        auction.setUser(user);
        auction.setLocation("Torun");
        auctionRepository.save(auction);
    }

}