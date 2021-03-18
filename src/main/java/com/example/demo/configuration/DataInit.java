package com.example.demo.configuration;

import com.example.demo.domain.model.*;
import com.example.demo.domain.repository.AuctionRepository;
import com.example.demo.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class DataInit {
    private PasswordEncoder passwordEncoder;
    private final AuctionRepository auctionRepository;
    private final UserRepository userRepository;

    public DataInit(PasswordEncoder passwordEncoder, AuctionRepository auctionRepository, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.auctionRepository = auctionRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        User user = new User();
        user.setAccountName("lukasz123");
        user.setPassword("pass");
        user.setCity("torun");
        user.setEmail("lukasz123@gmail.com");
        user.setStreet("XXX");
        user.setAccountStatus(AccountStatus.ACTIVE);
        user.setAccountType(AccountType.PREMIUM);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        Auction auction = new Auction();
        auction.setTitle("aukcja1");
        auction.setDescription("opis aukcji1");
        auction.setBuyNowPrice(new BigDecimal(200));
        auction.setActualPrice(new BigDecimal(160));
        auction.setCategory(Category.HOME);
        auction.setUser(user);
        auction.setLocation("Katowice");
        auction.setStartDateTime(LocalDateTime.now());
        auction.setEndDateTime(LocalDateTime.now().plusWeeks(1L));
        auction.setItemImageUrl("https://www.mediaexpert.pl/media/cache/filemanager_original/product/2/670/692/569/h4m4oqb2/icons/categories/ikony_rowery_i_skutery_3_poziom/Fat-Bike.jpg");
        auctionRepository.save(auction);
    }
}
