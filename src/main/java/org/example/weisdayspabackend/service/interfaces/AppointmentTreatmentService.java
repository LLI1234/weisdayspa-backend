package org.example.weisdayspabackend.service.interfaces;

import org.example.weisdayspabackend.entity.Treatment;
import org.example.weisdayspabackend.entity.junctiontable.AppointmentTreatment;

import java.util.List;

public interface AppointmentTreatmentService {
    AppointmentTreatment assignTreatmentToAppointment(Long treatmentId, Long appointmentId);
    List<Treatment> getTreatmentsByAppointment(Long appointmentId);
    // Update the treatment(s) in an appointment
    AppointmentTreatment updateAppointmentTreatment(Long appointmentId, Long oldTreatmentId, Long newTreatmentId);
    void deleteTreatmentAppointment(Long appointmentTreatmentId);
}
