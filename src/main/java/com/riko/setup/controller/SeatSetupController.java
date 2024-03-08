package com.riko.setup.controller;

import com.riko.setup.client.TheatreClient;
import com.riko.setup.client.dto.theatre.Theatre;
import com.riko.setup.client.service.TheatreClientService;
import com.riko.setup.model.SeatSetup;
import com.riko.setup.model.dto.SeatSetupMinDTO;
import com.riko.setup.model.enums.SeatType;
import com.riko.setup.repo.SeatSetupRepository;
import com.riko.setup.service.SeatSetupService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/seat")
@RequiredArgsConstructor
public class SeatSetupController {

    private final SeatSetupService seatSetupService;

    @GetMapping
    public ResponseEntity<List<SeatSetup>> getAllSeatSetups() {
        return ResponseEntity.ok(seatSetupService.getAllSeats());
    }

    @PostMapping
    public ResponseEntity<SeatSetup> createSeatSetup(@RequestBody SeatSetupMinDTO seatSetupMinDTO) {
        return ResponseEntity.ok(seatSetupService.createSeatSetup(seatSetupMinDTO));
    }

    @GetMapping("/theatre/{theatreId}/seat-type/{seatType}/hall")
    public ResponseEntity<SeatSetup> getSeatSetupByTheatreIdAndSeatTypeAndHallName(@PathVariable Long theatreId,
                                                                                         @PathVariable SeatType seatType,
                                                                                         @RequestParam String hallName) {
        return ResponseEntity.ok(seatSetupService.getSeatSetupByTheatreIdAndSeatTypeAndHallName(theatreId, seatType, hallName));
    }

    /*@GetMapping("/theatre/{theatreId}")
    public ResponseEntity<List<SeatSetup>> getSeatSetupByTheatreId(@PathVariable Long theatreId) {
        return ResponseEntity.ok(seatSetupService.getSeatSetupByTheatreId(theatreId));
    }

    @GetMapping("/seat-type/{seatType}")
    public ResponseEntity<List<SeatSetup>> getSeatSetupBySeatType(@PathVariable SeatType seatType) {
        return ResponseEntity.ok(seatSetupService.getSeatSetupBySeatType(seatType));
    }

    @GetMapping("/theatre/{theatreId}/seat-type/{seatType}")
    public ResponseEntity<List<SeatSetup>> getSeatSetupByTheatreIdAndSeatType(@PathVariable Long theatreId,
                                                                              @PathVariable SeatType seatType) {
        return ResponseEntity.ok(seatSetupService.getSeatSetupByTheatreIdAndSeatType(theatreId, seatType));
    }*/
}
