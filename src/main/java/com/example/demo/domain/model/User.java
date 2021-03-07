package com.example.demo.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;

    private String password;

    private String accountName;

    private String region;

    private String city;

    private String street;

    private int houseNumber;

    private String postalCode;

    private LocalDate created;
    @Enumerated(value = EnumType.STRING)
    private AccountStatus accountStatus;


    @Enumerated(value = EnumType.STRING)
    private AccountType accountType;
}
