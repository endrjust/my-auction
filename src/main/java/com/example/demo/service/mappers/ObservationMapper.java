package com.example.demo.service.mappers;

import com.example.demo.domain.model.Observation;
import com.example.demo.model.ObservationDto;
import org.springframework.stereotype.Component;

@Component
public class ObservationMapper {

    public Observation map(ObservationDto observationDto){
        Observation observation=new Observation();
        observation.setAuctionId(observationDto.getAuctionId());
        observation.setUserId(observationDto.getUserId());
        return observation;
    }

    public ObservationDto map(Observation observation){
        ObservationDto observationDto=new ObservationDto();
        observationDto.setAuctionId(observation.getAuctionId());
        observationDto.setUserId(observation.getUserId());

        return observationDto;
    }
}
