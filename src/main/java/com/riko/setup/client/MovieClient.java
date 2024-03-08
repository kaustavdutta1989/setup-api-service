package com.riko.setup.client;

import com.riko.setup.client.dto.movie.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
        name = "${feign.client.movie.name}",
        url = "${feign.client.movie.url}"
)
public interface MovieClient {

    @GetMapping
    ResponseEntity<List<Movie>> getAllMovies();

    @GetMapping("/{id}")
    ResponseEntity<Movie> getMovieById(@PathVariable Long id);

    @GetMapping("/search")
    ResponseEntity<List<Movie>> searchMovieByLanguageGenre(@RequestParam(required = false) String keyword,
                                                        @RequestParam(required = false) Long genreId,
                                                        @RequestParam(required = false) Long languageId);
}
