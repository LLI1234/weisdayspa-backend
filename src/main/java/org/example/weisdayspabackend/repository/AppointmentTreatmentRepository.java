package org.example.weisdayspabackend.repository;

import org.example.weisdayspabackend.entity.junctiontable.AppointmentTreatment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppointmentTreatmentRepository extends JpaRepository<AppointmentTreatment, Long> {
    List<AppointmentTreatment> findByAppointment_AppointmentId(Long appointmentId);
    Optional<AppointmentTreatment> findByAppointment_AppointmentId_AndTreatment_TreatmentId(Long appointmentId, Long treatmentId);
}
