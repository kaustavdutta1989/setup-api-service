package com.riko.setup.repo;

import com.riko.setup.model.SeatSetup;
import com.riko.setup.model.enums.SeatType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeatSetupRepository extends JpaRepository<SeatSetup, Long> {
    List<SeatSetup> findByTheatreSeatTypeTheatreId(Long theatreId);

    List<SeatSetup> findByTheatreSeatTypeSeatType(SeatType seatType);

    Optional<SeatSetup> findByTheatreSeatTypeTheatreIdAndTheatreSeatTypeSeatTypeAndTheatreSeatTypeHallName(
            Long theatreId, SeatType seatType, String hallLikeSearchKey);

    List<SeatSetup> findByTheatreSeatTypeTheatreIdAndTheatreSeatTypeSeatType(Long theatreId, SeatType seatType);
}
