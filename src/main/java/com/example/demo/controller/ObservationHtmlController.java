package com.example.demo.controller;

import com.example.demo.domain.model.Observation;
import com.example.demo.model.ObservationDto;
import com.example.demo.service.ObservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ObservationHtmlController {

    private final ObservationService observationService;

    public ObservationHtmlController(ObservationService observationService) {
        this.observationService = observationService;
    }

    @PostMapping("/addAuctionToObservations/{auctionId}")
    public String addAuctionToObservation(@PathVariable Long auctionId) {
        observationService.addByAuctionId(auctionId);
        return "redirect:/auctionDetail/" + auctionId;
    }

    @GetMapping("/allUserObservations")
    public String findAllUserObservations(Model model) {
        List<ObservationDto> allUserObservations = observationService.findAllUserObservations();
        model.addAttribute("userObservations", allUserObservations);
        return "userObservation";
    }

}
