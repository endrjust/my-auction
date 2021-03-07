package com.example.demo.model;


import com.example.demo.domain.model.AccountStatus;
import com.example.demo.domain.model.AccountType;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Data
public class UserDto {

    private String email;

    private String password;

    private String repeatedPassword;

    private String accountName;

    private String region;

    private String city;

    private String street;

    private int houseNumber;

    private String postalCode;

    private LocalDate created;

    private String accountStatus;

    private String accountType;



}
