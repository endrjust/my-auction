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

    @PostMapping("addUser")
    public String addUser(@ModelAttribute UserDto userDto, Model model) {

        try {
            userService.validateUserMoreDetails(userDto);
            userService.saveUser(userDto);
            return "redirect:users";
        } catch (InvalidRegistrationDataException e) {
            model.addAttribute("newUser", userDto);
            model.addAttribute("emptyName" , true);
            model.addAttribute("message", e.getMessage());
            return "userForm";
        } catch (AccountNameExistsException e) {
            model.addAttribute("newUser", userDto);
            model.addAttribute("accountNameExists" , true);
            model.addAttribute("message", e.getMessage());
            return "userForm";
        }
    }

    @GetMapping("registerUser")
    public String registerUser(Model model){
        model.addAttribute("newUser", new UserDto());
        return "registerUser";
    }

    @PostMapping("registerNewUser")
    public String registerUser(@ModelAttribute UserDto userDto, Model model){
        try {
            userService.validateUserRegistration(userDto);
            model.addAttribute("newUser", userDto);
            return "userForm";

        } catch (EmailExistsException e) {
            model.addAttribute("newUser", userDto);
            model.addAttribute("emailExists", true);
            model.addAttribute("message", e.getMessage());
            return "registerUser";

        } catch (InvalidRegistrationDataException e) {
            model.addAttribute("newUser", userDto);
            model.addAttribute("wrongPassword", true);
            model.addAttribute("message", e.getMessage());
            return "registerUser";
        }
    }
}
