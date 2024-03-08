package com.riko.setup.service.implementation;

import com.riko.setup.client.dto.theatre.Theatre;
import com.riko.setup.client.service.MovieClientService;
import com.riko.setup.model.ShowSetup;
import com.riko.setup.model.dto.ShowSetupMinDTO;
import com.riko.setup.model.enums.ShowTime;
import com.riko.setup.repo.ShowSetupRepository;
import com.riko.setup.service.ShowSetupService;
import com.riko.setup.client.service.TheatreClientService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShowSetupServiceImpl implements ShowSetupService {

    private final TheatreClientService theatreClientService;
    private final MovieClientService movieClientService;
    private final ShowSetupRepository showSetupRepository;

    public List<ShowSetup> getShowsByCitySearchMovie(Long cityId, String movieKey, Long genreId, Long languageId) throws BadRequestException {
        try {
            return showSetupRepository.findByMovieIdInAndTheatreIdIn(
                    movieClientService.getMovieIds(movieClientService.searchMovieByLanguageGenre(movieKey, genreId, languageId)),
                    theatreClientService.getTheatreIds(theatreClientService.getTheatresByCityId(cityId)));
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException("");
        }
    }

    public ShowSetup saveShow(ShowSetupMinDTO showSetupMinDTO) {
        movieClientService.getMovieById(showSetupMinDTO.getMovieId());
        theatreClientService.checkTheatre(showSetupMinDTO.getTheatreId());
        return showSetupRepository.save(ShowSetup.builder()
                .theatreId(showSetupMinDTO.getTheatreId())
                .movieId(showSetupMinDTO.getMovieId())
                .showDate(showSetupMinDTO.getShowDate())
                .showTime(showSetupMinDTO.getShowTime())
                .build());
    }

    public List<ShowSetup> getAllShows() {
        return showSetupRepository.findAll();
    }

    public ShowSetup getShowById(Long id) {
        Optional<ShowSetup> showSetup = showSetupRepository.findById(id);
        if(showSetup.isEmpty())
            throw new EntityNotFoundException("show setup not found with id: " + id);
        return showSetup.get();
    }

    public List<ShowSetup> getShowsByMovieAndCity(Long movieId, Long cityId, LocalDateTime localDateTime)
            throws BadRequestException {
        Set<Long> theatreIds;
        try {
            List<Theatre> cityTheatres = theatreClientService.getTheatresByCityId(cityId);
            if (cityTheatres != null)
                theatreIds = cityTheatres.stream()
                        .map(Theatre::getId)
                        .collect(Collectors.toSet());
            else throw new EntityNotFoundException("no theatres for this city");
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
        return showSetupRepository.findByMovieIdAndTheatreIdIn(movieId, theatreIds.stream().toList())
                .stream()
                .filter(showSetup -> isShowWithinTimeRange(showSetup, localDateTime, 7))
                .toList();
    }

    private boolean isShowWithinTimeRange(ShowSetup showSetup, LocalDateTime currentDateTime, Integer days) {
        return showSetup.getShowDate().isAfter(LocalDate.now())
                    && showSetup.getShowDate().isBefore(LocalDate.now().plusDays(days))
                && getShowTimeDateTime(showSetup).isAfter(currentDateTime);
    }

    private static LocalDateTime getShowTimeDateTime(ShowSetup show) {
        return LocalDateTime.of(show.getShowDate(), switch (show.getShowTime()) {
            case MORNING -> LocalTime.of(ShowTime.MORNING.getStartTime(), 0);
            case NOON -> LocalTime.of(ShowTime.NOON.getStartTime(), 0);
            case AFTERNOON -> LocalTime.of(ShowTime.AFTERNOON.getStartTime(), 0);
            case EVENING -> LocalTime.of(ShowTime.EVENING.getStartTime(), 0);
            case NIGHT -> LocalTime.of(ShowTime.NIGHT.getStartTime(), 0);
        });
    }
}
