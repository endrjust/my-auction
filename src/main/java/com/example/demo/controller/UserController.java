package com.example.demo.controller;


import com.example.demo.model.UserDto;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserDto> getAll(){
        return userService.findAll();
    }

    @GetMapping("/users/{userId}")
    public UserDto findUser (@PathVariable long userId){
       return userService.findUser(userId);
    }


    @PostMapping("/users")
    public UserDto saveUser(@RequestBody UserDto userDto){
       return  userService.saveUser(userDto);
    }

    @DeleteMapping("/Users/{userId}")
    public void deleteUSer(@PathVariable long userId){
        userService.deleteUser(userId);
    }

    @PutMapping("/users/{userId}")
        public void updateUser(@RequestBody UserDto userDto, @PathVariable long userId){
        userService.updateUser(userDto, userId);

    }
}









