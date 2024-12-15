package org.example.weisdayspabackend.service.implementations;

import org.example.weisdayspabackend.entity.Appointment;
import org.example.weisdayspabackend.entity.Treatment;
import org.example.weisdayspabackend.entity.junctiontable.AppointmentTreatment;
import org.example.weisdayspabackend.repository.AppointmentRepository;
import org.example.weisdayspabackend.repository.AppointmentTreatmentRepository;
import org.example.weisdayspabackend.repository.TreatmentRepository;
import org.example.weisdayspabackend.service.interfaces.AppointmentTreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentTreatmentServiceImpl implements AppointmentTreatmentService {

    private final AppointmentTreatmentRepository appointmentTreatmentRepository;
    private final TreatmentRepository treatmentRepository;
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentTreatmentServiceImpl(
            AppointmentTreatmentRepository appointmentTreatmentRepository,
            TreatmentRepository treatmentRepository,
            AppointmentRepository appointmentRepository
    ) {
        this.appointmentTreatmentRepository = appointmentTreatmentRepository;
        this.treatmentRepository = treatmentRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public AppointmentTreatment assignTreatmentToAppointment(Long treatmentId, Long appointmentId) {
        Treatment treatment = treatmentRepository.findById(treatmentId)
                .orElseThrow(() -> new RuntimeException("Treatment not found with id: " + treatmentId));
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + appointmentId));

        AppointmentTreatment appointmentTreatment = new AppointmentTreatment();
        appointmentTreatment.setAppointment(appointment);
        appointmentTreatment.setTreatment(treatment);
        return appointmentTreatmentRepository.save(appointmentTreatment);
    }

    @Override
    public List<Treatment> getTreatmentsByAppointment(Long appointmentId) {
        List<AppointmentTreatment> appointmentTreatments = appointmentTreatmentRepository.findByAppointment_AppointmentId(appointmentId);
        return appointmentTreatments.stream()
                .map(AppointmentTreatment::getTreatment) // Extract the Treatment from each AppointmentTreatment
                .collect(Collectors.toList()); // Collect as a List<Treatment>
    }

    @Override
    public AppointmentTreatment updateAppointmentTreatment(Long appointmentId, Long oldTreatmentId, Long newTreatmentId) {
        // Fetch the existing AppointmentTreatment relationship
        AppointmentTreatment existingAppointmentTreatment = appointmentTreatmentRepository
                .findByAppointment_AppointmentId_AndTreatment_TreatmentId(appointmentId, oldTreatmentId)
                .orElseThrow(() -> new RuntimeException("Appointment-Treatment relationship not found"));

        // Fetch the new Treatment
        Treatment newTreatment = treatmentRepository.findById(newTreatmentId)
                .orElseThrow(() -> new RuntimeException("Treatment not found with id: " + newTreatmentId));

        // Update the Treatment in the existing AppointmentTreatment
        existingAppointmentTreatment.setTreatment(newTreatment);

        // Save and return the updated AppointmentTreatment
        return appointmentTreatmentRepository.save(existingAppointmentTreatment);
    }


    @Override
    public void deleteTreatmentAppointment(Long appointmentTreatmentId) {
        AppointmentTreatment appointmentTreatment = appointmentTreatmentRepository
                .findById(appointmentTreatmentId)
                .orElseThrow(() -> new RuntimeException("Appointment-Treatment relation not found"));
        appointmentTreatmentRepository.delete(appointmentTreatment);
    }
}
