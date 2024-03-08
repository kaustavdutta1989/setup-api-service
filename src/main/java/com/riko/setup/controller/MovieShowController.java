package com.riko.setup.controller;

import com.riko.setup.model.ShowSetup;
import com.riko.setup.model.dto.MovieCityTimeDTO;
import com.riko.setup.service.ShowSetupService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/show/movie")
@RequiredArgsConstructor
public class MovieShowController {

    private final ShowSetupService showSetupService;

    @GetMapping("/search")
    public ResponseEntity<List<ShowSetup>> getShowsByCityMovieId(@RequestParam Long cityId,
                                                                 @RequestParam(required = false) String movieKey,
                                                                 @RequestParam(required = false) Long genreId,
                                                                 @RequestParam(required = false) Long languageId) throws BadRequestException {
        return ResponseEntity.ok(showSetupService.getShowsByCitySearchMovie(cityId, movieKey, genreId, languageId));
    }

    @PostMapping
    public ResponseEntity<List<ShowSetup>> getShowsByMovieAndCity(@RequestBody MovieCityTimeDTO movieCityTimeDTO)
            throws BadRequestException {
        return ResponseEntity.ok(showSetupService.getShowsByMovieAndCity(
                movieCityTimeDTO.getMovieId(), movieCityTimeDTO.getCityId(),
                movieCityTimeDTO.getLocalDateTime() != null ? movieCityTimeDTO.getLocalDateTime() : LocalDateTime.now()
        ));
    }
}
