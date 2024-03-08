package com.riko.setup.client.service.implementation;

import com.riko.setup.client.dto.theatre.Theatre;
import com.riko.setup.client.TheatreClient;
import com.riko.setup.client.service.TheatreClientService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TheatreClientServiceImpl implements TheatreClientService {

    private final TheatreClient theatreClient;

    public List<Theatre> getTheatresByCityId(Long cityId) throws BadRequestException {
        try {
            ResponseEntity<List<Theatre>> theatres = theatreClient.getTheatreByCityId(cityId);
            log.info("theatres: {}", Objects.requireNonNull(theatres.getBody()).stream());
            return theatres.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException("could not fetch theatres");
        }
    }

    public Theatre checkTheatre(Long id) {
        try {
            ResponseEntity<Theatre> theatre = theatreClient.getTheatreById(id);
            log.info("theatre found {} ", Objects.requireNonNull(theatre.getBody()));
            return theatre.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            throw new EntityNotFoundException("theatre not found from client with id: " + id);
        }
    }

    public List<Long> getTheatreIds(List<Theatre> cityTheatres) {
        if (cityTheatres != null)
            return cityTheatres.stream().map(Theatre::getId).collect(Collectors.toSet()).stream().toList();
        else return new ArrayList<>();
    }
}
