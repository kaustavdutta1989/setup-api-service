package com.riko.setup.controller;

import com.riko.setup.model.SeatSetup;
import com.riko.setup.model.enums.SeatType;
import com.riko.setup.service.SeatSetupService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/seat/theatre")
public class TheatreSeatController {

    private final SeatSetupService seatSetupService;

    @GetMapping
    public ResponseEntity<List<SeatSetup>> searchSeatSetup(@RequestParam(required = false) Long theatreId,
                                                           @RequestParam(required = false) SeatType seatType,
                                                           @RequestParam(required = false) String hallLikeSearchKey) {
        return ResponseEntity.ok(seatSetupService.searchSeatSetupByTheatreIdAndSeatTypeAndHallName(
                theatreId,
                seatType,
                hallLikeSearchKey));
    }
}
