package org.example.weisdayspabackend.service.implementations;

import org.example.weisdayspabackend.entity.Appointment;
import org.example.weisdayspabackend.repository.AppointmentRepository;
import org.example.weisdayspabackend.service.interfaces.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public Optional<Appointment> getAppointment(Long appointmentId) {
        return appointmentRepository.findById(appointmentId);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public List<Appointment> getDailyAppointment(LocalDate date) {
        return appointmentRepository.findByDate(date);
    }

    @Override
    public Appointment updateAppointment(Long appointmentId, Appointment updatedAppointment) {
        Appointment existingAppointment = getAppointment(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment with id " + appointmentId + " not found"));

        if(Objects.nonNull(updatedAppointment.getDate())) {
            existingAppointment.setDate(updatedAppointment.getDate());
        }
        if(Objects.nonNull(updatedAppointment.getStartTime())) {
            existingAppointment.setStartTime(updatedAppointment.getStartTime());
        }
        if(Objects.nonNull(updatedAppointment.getDuration())) {
            existingAppointment.setDuration(updatedAppointment.getDuration());
        }
        if(Objects.nonNull(updatedAppointment.getStatus())) {
            existingAppointment.setStatus(updatedAppointment.getStatus());
        }
        if(Objects.nonNull(updatedAppointment.getGuestFirstNames()) && Objects.nonNull(updatedAppointment.getGuestLastNames())) {
            existingAppointment.setGuestFirstNames(updatedAppointment.getGuestFirstNames());
            existingAppointment.setGuestLastNames(updatedAppointment.getGuestLastNames());
        }
        if(Objects.nonNull(updatedAppointment.getGuestEmails())) {
            existingAppointment.setGuestEmails(updatedAppointment.getGuestEmails());
        }
        return appointmentRepository.save(existingAppointment);
    }

    @Override
    public void deleteAppointment(Long appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }

}
