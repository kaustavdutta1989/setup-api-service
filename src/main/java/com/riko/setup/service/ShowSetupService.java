package com.riko.setup.service;

import com.riko.setup.model.ShowSetup;
import com.riko.setup.model.dto.ShowSetupMinDTO;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

public interface ShowSetupService {

    List<ShowSetup> getShowsByCitySearchMovie(@RequestParam Long cityId,
                                           @RequestParam(required = false) String movieKey,
                                           @RequestParam(required = false) Long genreId,
                                           @RequestParam(required = false) Long languageId) throws BadRequestException;

    ShowSetup saveShow(ShowSetupMinDTO showSetupMinDTO);

    List<ShowSetup> getAllShows();

    ShowSetup getShowById(Long id);

    List<ShowSetup> getShowsByMovieAndCity(Long movieId, Long cityId, LocalDateTime localDateTime) throws BadRequestException;
}
