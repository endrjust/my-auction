package com.example.demo.service.mappers;

import com.example.demo.domain.model.AccountStatus;
import com.example.demo.domain.model.AccountType;
import com.example.demo.domain.model.User;
import com.example.demo.model.UserDto;

import java.time.LocalDate;

public class UserMapper {

    public User map(UserDto userDto){
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAccountName(userDto.getAccountName());
        user.setRegion(userDto.getRegion());
        user.setCity(userDto.getCity());
        user.setStreet(user.getStreet());
        user.setHouseNumber(userDto.getHouseNumber());
        user.setPostalCode(userDto.getPostalCode());
        user.setCreated(LocalDate.now());
        user.setAccountStatus(AccountStatus.ACTIVE);
        user.setAccountType(AccountType.STANDARD);
        return user;
    }

    public UserDto map(User user){
        UserDto userDto = new UserDto();
        userDto.setAccountName(user.getAccountName());
        userDto.setRegion(user.getRegion());
        userDto.setCity(user.getCity());
        userDto.setStreet(user.getStreet());
        userDto.setHouseNumber(user.getHouseNumber());
        userDto.setPostalCode(user.getPostalCode());
        userDto.setCreated(user.getCreated());
        userDto.setAccountStatus(user.getAccountStatus().toString());
        userDto.setAccountType(user.getAccountType().toString());
        return userDto;
    }
}
