package com.example.demo.controller;

import com.example.demo.domain.model.User;
import com.example.demo.exception.AccountNameExistsException;
import com.example.demo.exception.EmailExistsException;
import com.example.demo.exception.InvalidRegistrationDataException;
import com.example.demo.model.ObservationDto;
import com.example.demo.model.UserDto;
import com.example.demo.service.ObservationService;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserHtmlConrtoller {

    private UserService userService;
    private ObservationService observationService;

    public UserHtmlConrtoller(UserService userService, ObservationService observationService) {
        this.userService = userService;
        this.observationService = observationService;
    }

    @GetMapping("/users")
    public String allUsers(Model model) {
        List<UserDto> usersList = userService.findAll();
        model.addAttribute("users", usersList);
        return "users";
    }

    @GetMapping("/userProfile") //todo:do sprawdzenia
    public String getUserProfile(Model model) {
        User userEntity = userService.findUserEntity();
        model.addAttribute("userDetails", userEntity);
        return "/userProfile";
    }

    @PostMapping("/userProfile") //todo:do sprawdzenia
    public String updateUserProfile(@ModelAttribute UserDto userDto,Model model) {
        User updatedUser = userService.updateUser(userDto);
        model.addAttribute("updatedUser",updatedUser);
        return "redirect:userProfile";
    }

    @PostMapping("/addUser")
    public String addUser(@Valid @ModelAttribute UserDto userDto, Model model) {

        try {
            userService.validateUserMoreDetails(userDto);
            userService.saveUser(userDto);
            return "redirect:/";
        } catch (InvalidRegistrationDataException e) {
            model.addAttribute("newUser", userDto);
            model.addAttribute("emptyName", true);
            model.addAttribute("message", e.getMessage());
            return "userForm";
        } catch (AccountNameExistsException e) {
            model.addAttribute("newUser", userDto);
            model.addAttribute("accountNameExists", true);
            model.addAttribute("message", e.getMessage());
            return "userForm";
        }
    }

    @GetMapping("/registerUser")
    public String registerUser(Model model) {
        model.addAttribute("newUser", new UserDto());
        return "registerUser";
    }

    @PostMapping("/registerNewUser")
    public String registerUser(@Valid @ModelAttribute UserDto userDto, Model model) {
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

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @GetMapping("/profile")
    public String getUserProfilePage(Model model){
        User user = userService.findUserEntity();
        List<ObservationDto> allUserObservations = observationService.findAllUserObservations();
        model.addAttribute("user", user);
        model.addAttribute("userObservations", allUserObservations);
        return "profilPage";
    }

    @GetMapping("/user-profile")
    public String showUserEditProfileForm(Model model){
        User user = userService.findUserEntity();
        model.addAttribute("userDetails", user);
        return "userProfile";
    }

    @PostMapping("/userEditProfile")
    public String editUserProfile(@ModelAttribute UserDto userDto, Model model){
      userService.updateUser(userDto);
      return "redirect:/profile";
    }


}
