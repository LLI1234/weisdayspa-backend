package org.example.weisdayspabackend.service.interfaces;

import org.example.weisdayspabackend.entity.Appointment;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    Appointment createAppointment(Appointment appointment);
    Optional<Appointment> getAppointment(Long appointmentId);
    List<Appointment> getAllAppointments();
    List<Appointment> getDailyAppointment(LocalDate date);
    Appointment updateAppointment(Long appointmentId, Appointment updatedAppointment);
    void deleteAppointment(Long appointmentId);

}