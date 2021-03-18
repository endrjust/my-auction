package com.example.demo.model;


import lombok.Data;

import javax.validation.constraints.Email;
import java.time.LocalDate;

@Data
public class UserDto {

    @Email(message = "Has to be an email")
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
