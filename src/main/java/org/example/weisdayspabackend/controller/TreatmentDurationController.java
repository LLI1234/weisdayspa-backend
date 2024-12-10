package org.example.weisdayspabackend.controller;

import org.example.weisdayspabackend.entity.TreatmentDuration;
import org.example.weisdayspabackend.service.TreatmentDurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/durations")
public class TreatmentDurationController {

    private final TreatmentDurationService duration_service;

    @Autowired
    public TreatmentDurationController(TreatmentDurationService duration_service) {
        this.duration_service = duration_service;
    }

    @PostMapping
    public ResponseEntity<TreatmentDuration> createDuration(@RequestBody TreatmentDuration duration) {
        TreatmentDuration new_duration = duration_service.createDuration(duration);
        return new ResponseEntity<>(new_duration, HttpStatus.CREATED);
    }

    @GetMapping("/{treatmentId}")
    public ResponseEntity<List<TreatmentDuration>> getDurationsByTreatments(@PathVariable Long treatmentId) {
        return new ResponseEntity<>(duration_service.getDurationsByTreatment(treatmentId), HttpStatus.OK);
    }

    @PutMapping("/{durationId}")
    public ResponseEntity<TreatmentDuration> updateDuration(@PathVariable Long durationId, @RequestBody TreatmentDuration updated_duration) {
        return new ResponseEntity<>(duration_service.updateDuration(durationId, updated_duration), HttpStatus.OK);
    }

    @DeleteMapping("/{durationId}")
    public ResponseEntity<Void> deleteDuration(@PathVariable Long durationId) {
        duration_service.deleteDuration(durationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
