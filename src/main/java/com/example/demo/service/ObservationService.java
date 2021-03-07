package com.example.demo.service;

import com.example.demo.domain.model.Observation;
import com.example.demo.domain.repository.ObservationRepository;
import com.example.demo.exception.ObservationNotFoundException;
import com.example.demo.model.ObservationDto;
import com.example.demo.service.mappers.ObservationMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ObservationService {
    private final ObservationRepository observationRepository;
    private final ObservationMapper observationMapper;

    public ObservationService(ObservationRepository observationRepository, ObservationMapper observationMapper) {
        this.observationRepository = observationRepository;
        this.observationMapper = observationMapper;
    }

    public ObservationDto add(ObservationDto observationDto) {
        if (observationRepository.existsByAuctionIdAndUserId(observationDto.getAuctionId(), observationDto.getUserId())) {
            throw new ObservationNotFoundException("Już obserwujesz tą aukcję");
        }

        Observation observation = observationMapper.map(observationDto);
        observation.setStartObservationDate(LocalDateTime.now());
        return observationMapper.map(observationRepository.save(observation));
    }

    public void delete(ObservationDto observationDto) {
        if (!observationRepository.existsByAuctionIdAndUserId(observationDto.getAuctionId(), observationDto.getUserId())) {
            throw new ObservationNotFoundException("Nie obserwujesz takiej aukcji!");
        }
        observationRepository.deleteByAuctionIdAndUserId(observationDto.getAuctionId(), observationDto.getUserId());
    }

    public List<ObservationDto> findAllUserObservations(long userId) {
        return observationRepository.findAllByUserId(userId).stream()
                .map(observationMapper::map).collect(Collectors.toList());
    }
}
