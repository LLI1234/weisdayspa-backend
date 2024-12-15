package org.example.weisdayspabackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.weisdayspabackend.entity.junctiontable.AppointmentEmployee;
import org.example.weisdayspabackend.entity.junctiontable.AppointmentTreatment;
import org.example.weisdayspabackend.enumerator.Status;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue
    private Long appointmentId;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private LocalTime startTime;
    @Column(nullable = false)
    private Long duration;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ElementCollection
    private Set<String> guestFirstNames;
    @ElementCollection
    private Set<String> guestLastNames;
    @ElementCollection
    private Set<String> guestEmails;

    @OneToMany(mappedBy = "appointment", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<AppointmentTreatment> appointmentTreatments;

    @OneToMany(mappedBy = "appointment", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<AppointmentEmployee> appointmentEmployees;
}
