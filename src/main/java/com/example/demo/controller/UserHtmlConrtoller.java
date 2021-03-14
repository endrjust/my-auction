package com.example.demo.controller;

import com.example.demo.model.UserDto;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserHtmlConrtoller {

    private UserService userService;

    public UserHtmlConrtoller(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("/users")
        public String usersPage(Model model){
        List<UserDto> usersList = userService.findAll();
        model.addAttribute("users", usersList);
        return "users";
    }

    @GetMapping("/userForm")
    public String addUser(Model model) {
        model.addAttribute("newUser",new UserDto());
        return "userForm";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute UserDto userDto) {
        userService.saveUser(userDto);
        return "redirect:userForm";
    }

}
