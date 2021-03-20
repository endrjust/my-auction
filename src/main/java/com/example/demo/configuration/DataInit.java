package com.example.demo.configuration;

import com.example.demo.domain.model.*;
import com.example.demo.domain.repository.AuctionRepository;
import com.example.demo.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
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
        User user1 = new User();
        user1.setAccountName("lukasz123");
        user1.setPassword("pass");
        user1.setCity("Torun");
        user1.setEmail("lukasz123@gmail.com");
        user1.setStreet("XXX");
        user1.setAccountStatus(AccountStatus.ACTIVE);
        user1.setAccountType(AccountType.PREMIUM);
        user1.setPassword(passwordEncoder.encode(user1.getPassword()));
        userRepository.save(user1);

        User user2 = new User();
        user2.setAccountName("andrzej");
        user2.setPassword("pass");
        user2.setCity("Katowice");
        user2.setEmail("andrzej@wp.pl");
        user2.setStreet("XXX");
        user2.setAccountStatus(AccountStatus.ACTIVE);
        user2.setAccountType(AccountType.PREMIUM);
        user2.setPassword(passwordEncoder.encode(user2.getPassword()));
        userRepository.save(user2);

        Auction auction1 = new Auction();
        auction1.setTitle("aukcja1");
        auction1.setDescription("opis aukcji1");
        auction1.setBuyNowPrice(new BigDecimal(200));
        auction1.setActualPrice(new BigDecimal(160));
        auction1.setCategory(Category.HOME);
        auction1.setStartDateTime(LocalDateTime.now());
        auction1.setEndDateTime(LocalDateTime.now().plusDays(3));
        auction1.setUser(user1);
        auction1.setItemImageUrl("https://mrozbike.pl/images/Rower%20g%C3%B3rski%20Focus%20O1E%208.7%202020.jpg");
        auction1.setLocation(user1.getCity());
        auction1.setBuyNowEnable(true);

        auctionRepository.save(auction1);

        Auction auction2 = new Auction();
        auction2.setTitle("aukcja2");
        auction2.setDescription("opis aukcji2");
        auction2.setBuyNowPrice(new BigDecimal(200));
        auction2.setActualPrice(new BigDecimal(160));
        auction2.setCategory(Category.MOTO);
        auction2.setUser(user2);
        auction2.setItemImageUrl("https://f00.osfr.pl/foto/5/48315060977/4cab67358c53023725fe48019197864/activejet-komputer-i5-16gb-512ssd-1660ti-w10,48315060977_7.jpg");
        auction2.setStartDateTime(LocalDateTime.now());
        auction2.setEndDateTime(LocalDateTime.now().plusDays(6));
        auction2.setLocation("Torun");
        auction2.setBuyNowEnable(true);

        auctionRepository.save(auction2);

        Auction auction3 = new Auction();
        auction3.setTitle("aukcja3");
        auction3.setDescription("zakończona aukcja");
        auction3.setBuyNowPrice(new BigDecimal(50));
        auction3.setActualPrice(new BigDecimal(15));
        auction3.setCategory(Category.CLOTHES);
        auction3.setUser(user1);
        auction3.setItemImageUrl("https://mrozbike.pl/images/SIS-Baton-Energetyczny-Jab%C5%82ko-Porzeczka-40-g.jpg");
        auction3.setStartDateTime(LocalDateTime.now().minusDays(8));
        auction3.setEndDateTime(LocalDateTime.now().minusDays(1));
        auction3.setLocation("Torun");
        auction3.setBuyNowEnable(false);


        auctionRepository.save(auction3);
    }
}
