package com.example.demo.controller;

import com.example.demo.model.AuctionDto;
import com.example.demo.model.UserDto;
import com.example.demo.service.AuctionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AuctionHtmlController {

    AuctionService auctionService;

    public AuctionHtmlController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<AuctionDto> auctionsList = auctionService.findAll();
        model.addAttribute("auctions", auctionsList);
        return "index";
    }

    @GetMapping("/auctionDetail")
    public String auctionDetails(){
        return "auctionDetail";
    }


    @GetMapping("auctionForm")
    public String addUser(Model model) {
        model.addAttribute("newAuction", new AuctionDto());
        return "auctionForm";
    }

    @PostMapping("addAuction")
    public String addUser(@ModelAttribute AuctionDto auctionDto) {
        auctionService.saveAuction(auctionDto);
        return "redirect:auctionForm";
    }
}
