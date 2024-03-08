package com.riko.setup.client.service;

import com.riko.setup.client.dto.theatre.Theatre;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface TheatreClientService {

    List<Theatre> getTheatresByCityId(Long cityId) throws BadRequestException;
    Theatre checkTheatre(Long id);
    List<Long> getTheatreIds(List<Theatre> cityTheatres);
}
