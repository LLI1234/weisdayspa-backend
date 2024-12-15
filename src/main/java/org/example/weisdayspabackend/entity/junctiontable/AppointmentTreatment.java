package org.example.weisdayspabackend.entity.junctiontable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.weisdayspabackend.entity.Appointment;
import org.example.weisdayspabackend.entity.Treatment;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentTreatment {
    @Id
    @GeneratedValue
    private Long appointmentTreatmentId;

    @ManyToOne
    @JoinColumn(name = "appointmentId", nullable = false)
    private Appointment appointment;

    @ManyToOne
    @JoinColumn(name = "treatmentId", nullable = false)
    private Treatment treatment;
}
