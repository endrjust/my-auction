package com.example.demo.controller;


import com.example.demo.exception.EmailExistsException;
import com.example.demo.model.UserDto;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getAll(){
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    public UserDto findUser (@PathVariable long userId){
       return userService.findUser(userId);
    }


    @PostMapping
    public UserDto saveUser(@RequestBody UserDto userDto) throws EmailExistsException {
       return  userService.saveUser(userDto);
    }

    @DeleteMapping("/{userId}")
    public void deleteUSer(@PathVariable long userId){
        userService.deleteUser(userId);
    }

    @PutMapping("/{userId}")
        public void updateUser(@RequestBody UserDto userDto, @PathVariable long userId){
        userService.updateUser(userDto, userId);

    }
}









