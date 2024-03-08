package com.riko.setup.client.service;

import com.riko.setup.client.dto.movie.Movie;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface MovieClientService {
    List<Movie> getAllMovies();
    Movie getMovieById(Long id);
    List<Movie> searchMovieByLanguageGenre(String movieKey, Long genreId, Long languageId) throws BadRequestException;
    List<Long> getMovieIds(List<Movie> searchedMovies);
}
