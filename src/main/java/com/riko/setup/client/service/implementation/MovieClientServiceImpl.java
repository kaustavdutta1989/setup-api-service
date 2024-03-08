package com.riko.setup.client.service.implementation;

import com.riko.setup.client.MovieClient;
import com.riko.setup.client.dto.movie.Movie;
import com.riko.setup.client.service.MovieClientService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovieClientServiceImpl implements MovieClientService {

    private final MovieClient movieClient;

    @Override
    public List<Movie> getAllMovies() {
        ResponseEntity<List<Movie>> moviesRE = movieClient.getAllMovies();
        log.info("movies: " + Objects.requireNonNull(moviesRE.getBody()).stream());
        return moviesRE.getBody();
    }

    @Override
    public Movie getMovieById(Long id) {
        try {
            ResponseEntity<Movie> movieRE = movieClient.getMovieById(id);
            log.info("movie found {}", movieRE);
            return movieRE.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            throw new EntityNotFoundException("movie not found with id: " + id);
        }
    }

    @Override
    public List<Movie> searchMovieByLanguageGenre(String movieKey,
                                                  Long genreId,
                                                  Long languageId) throws BadRequestException {
        try {
            return movieClient.searchMovieByLanguageGenre(
                    movieKey,
                    genreId,
                    languageId).getBody();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException("could not fetch searched movies");
        }
    }

    @Override
    public List<Long> getMovieIds(List<Movie> searchedMovies) {
        if(searchedMovies != null)
            return searchedMovies.stream().map(Movie::getId).collect(Collectors.toSet()).stream().toList();
        else return new ArrayList<>();
    }
}
