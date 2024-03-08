package com.riko.setup.repo;

import com.riko.setup.model.ShowSetup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowSetupRepository extends JpaRepository<ShowSetup, Long> {
    List<ShowSetup> findByMovieId(Long movieId);
    List<ShowSetup> findByMovieIdAndTheatreIdIn(Long movieId, List<Long> theatreIds);
    List<ShowSetup> findByMovieIdInAndTheatreIdIn(List<Long> moviesIds, List<Long> theatreIds);
}
