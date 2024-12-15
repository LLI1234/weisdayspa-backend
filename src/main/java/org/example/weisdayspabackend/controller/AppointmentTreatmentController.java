package org.example.weisdayspabackend.controller;

import org.example.weisdayspabackend.entity.Treatment;
import org.example.weisdayspabackend.entity.junctiontable.AppointmentTreatment;
import org.example.weisdayspabackend.service.interfaces.AppointmentTreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/appointment-treatments")
public class AppointmentTreatmentController {

    private final AppointmentTreatmentService appointmentTreatmentService;
    @Autowired
    public AppointmentTreatmentController(AppointmentTreatmentService appointmentTreatmentService) {
        this.appointmentTreatmentService = appointmentTreatmentService;
    }

    @PostMapping
    public ResponseEntity<AppointmentTreatment> createAppointmentTreatment(@RequestBody Map<String, Long> request) {
        Long appointmentId = request.get("appointmentId");
        Long treatmentId = request.get("treatmentId");
        AppointmentTreatment appointmentTreatment = appointmentTreatmentService.assignTreatmentToAppointment(treatmentId, appointmentId);
        return new ResponseEntity<>(appointmentTreatment, HttpStatus.CREATED);
    }

    @GetMapping("/appointments/{appointmentId}")
    public ResponseEntity<List<Treatment>> getTreatmentsByAppointment(@PathVariable Long appointmentId) {
        return new ResponseEntity<>(appointmentTreatmentService.getTreatmentsByAppointment(appointmentId), HttpStatus.OK);
    }

    @PutMapping("/appointments/{appointmentId}/treatments/{oldTreatmentId}")
    public ResponseEntity<AppointmentTreatment> updateAppointmentTreatment(
            @PathVariable Long appointmentId,
            @PathVariable Long oldTreatmentId,
            @RequestBody Long newTreatmentId
    ) {
       return new ResponseEntity<>(appointmentTreatmentService.updateAppointmentTreatment(appointmentId, oldTreatmentId, newTreatmentId), HttpStatus.OK);
    }

    @DeleteMapping("/{appointmentTreatmentId}")
    public ResponseEntity<Void> deleteTreatmentAppointment(@PathVariable Long appointmentTreatmentId) {
        appointmentTreatmentService.deleteTreatmentAppointment(appointmentTreatmentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
