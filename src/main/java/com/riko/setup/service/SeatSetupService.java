package com.riko.setup.service;

import com.riko.setup.model.SeatSetup;
import com.riko.setup.model.composite.TheatreSeatTypeId;
import com.riko.setup.model.dto.SeatSetupMinDTO;
import com.riko.setup.model.enums.SeatType;

import java.util.List;

public interface SeatSetupService {
    List<SeatSetup> getAllSeats();
    SeatSetup createSeatSetup(SeatSetupMinDTO seatSetupMinDTO);
    List<SeatSetup> getSeatSetupByTheatreId(Long theatreId);
    List<SeatSetup> getSeatSetupBySeatType(SeatType seatType);
    List<SeatSetup> getSeatSetupByTheatreIdAndSeatType(Long theatreId, SeatType seatType);
    SeatSetup getSeatSetupByTheatreIdAndSeatTypeAndHallName(Long theatreId, SeatType seatType, String hallNameKey);
    List<SeatSetup> searchSeatSetupByTheatreIdAndSeatTypeAndHallName(Long theatreId, SeatType seatType, String hallNameKey);
}
