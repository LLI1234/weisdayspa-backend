package org.example.weisdayspabackend.controller;

import org.example.weisdayspabackend.entity.Treatment;
import org.example.weisdayspabackend.service.interfaces.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/treatments")
public class TreatmentController {

    private final TreatmentService treatment_service;

    @Autowired
    public TreatmentController(TreatmentService treatment_service) {
        this.treatment_service = treatment_service;
    }

    @PostMapping
    public ResponseEntity<Treatment> createTreatment(@RequestBody Treatment treatment) {
        Treatment new_treatment = treatment_service.createTreatment(treatment);
        return new ResponseEntity<>(new_treatment, HttpStatus.CREATED);
    }

    @GetMapping("/{treatment_id}")
    public ResponseEntity<Treatment> getTreatment(@PathVariable Long treatment_id) {
        return treatment_service.getTreatment(treatment_id)
                .map(treatment -> new ResponseEntity<>(treatment, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Treatment>> getAllTreatments() {
        return new ResponseEntity<>(treatment_service.getAllTreatments(), HttpStatus.OK);
    }

    @PutMapping("/{treatment_id}")
    public ResponseEntity<Treatment> updateTreatment(@PathVariable Long treatment_id, @RequestBody Treatment updated_treatment) {
        try {
            Treatment treatment = treatment_service.updateTreatment(treatment_id, updated_treatment);
            return new ResponseEntity<>(treatment, HttpStatus.OK);
        }
        catch(RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{treatment_id}")
    public ResponseEntity<Void> deleteTreatment(@PathVariable Long treatment_id) {
        try {
            treatment_service.deleteTreatment(treatment_id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
