package com.example.demo.controller;

import com.example.demo.domain.model.Category;
import com.example.demo.exception.AuctionNotFoundException;
import com.example.demo.model.AuctionDto;
import com.example.demo.model.BiddingDto;
import com.example.demo.service.AuctionService;
import com.example.demo.service.BiddingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AuctionHtmlController {

    AuctionService auctionService;
    BiddingService biddingService;

    public AuctionHtmlController(AuctionService auctionService, BiddingService biddingService) {
        this.auctionService = auctionService;
        this.biddingService = biddingService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<AuctionDto> auctionsList = auctionService.findAll();
        model.addAttribute("auctions", auctionsList);
        return "index";
    }

    @GetMapping("/auctionDetail/{auctionTitle}")
    public String auctionDetails(Model model, @PathVariable String auctionTitle) {
        AuctionDto auctionDto = auctionService.findAllByTitle(auctionTitle).
                stream().findFirst().orElseThrow(() -> new AuctionNotFoundException("Auction not exists"));
        Iterable<BiddingDto> allBids = biddingService.findAllBids();
        model.addAttribute("auction", auctionDto);
        model.addAttribute("newBid", new BiddingDto());
        model.addAttribute("auctionTitle", auctionDto.getTitle());
        model.addAttribute("bids", allBids);
        return "auctionDetail";
    }

    @GetMapping("auctionForm")
    public String addAuctionForm(Model model) {
        model.addAttribute("newAuction", new AuctionDto());
        return "auctionForm";
    }

    @PostMapping("addAuction")
    public String addAuctionPost(@ModelAttribute AuctionDto auctionDto) {
        auctionService.saveAuction(auctionDto);
        return "redirect:auctionForm";
    }

    @PostMapping("makeBid")
    public String makeBid(@ModelAttribute BiddingDto biddingDto) {
        biddingService.makeBid(biddingDto);
        return "redirect:/";
    }
    @GetMapping("/category/{categoryName}")
    public String findByCategory(@PathVariable("categoryName")String categoryName,Model model ){
        List<AuctionDto> allByCategory = auctionService.findAllByCategory(Category.valueOf(categoryName));
        model.addAttribute("auctions",allByCategory);
        return "index";
    }

}
