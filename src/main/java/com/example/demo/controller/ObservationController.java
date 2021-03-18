package com.example.demo.controller;

import com.example.demo.domain.model.Observation;
import com.example.demo.model.ObservationDto;
import com.example.demo.service.ObservationService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/observation")
public class ObservationController {
    private final ObservationService observationService;

    public ObservationController(ObservationService observationService) {
        this.observationService = observationService;
    }

    @PostMapping
    public ObservationDto add(@Valid @RequestBody ObservationDto observationDto){
        return observationService.add(observationDto);
    }

    @DeleteMapping
    public void delete(@Valid @RequestBody ObservationDto observationDto){
        observationService.delete(observationDto);
    }
    @GetMapping( path = "/{userId}")
    public List<ObservationDto> findAllUserObservations(@PathVariable long userId){
        return observationService.findAllUserObservations(userId);
    }

}
