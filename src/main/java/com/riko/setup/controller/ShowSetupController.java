package com.riko.setup.controller;

import com.riko.setup.model.ShowSetup;
import com.riko.setup.model.dto.ShowSetupMinDTO;
import com.riko.setup.service.ShowSetupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/show")
@RequiredArgsConstructor
public class ShowSetupController {

    private final ShowSetupService showSetupService;

    @GetMapping
    public ResponseEntity<List<ShowSetup>> getAllShowSetups() {
        return ResponseEntity.ok(showSetupService.getAllShows());
    }

    @PostMapping
    public ResponseEntity<ShowSetup> createShowSetup(@RequestBody ShowSetupMinDTO showSetupMinDTO) {
        return ResponseEntity.ok(showSetupService.saveShow(showSetupMinDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowSetup> getShowSetupById(@PathVariable Long id) {
        return ResponseEntity.ok(showSetupService.getShowById(id));
    }
}
