package org.example.weisdayspabackend.entity.junctiontable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.weisdayspabackend.entity.Appointment;
import org.example.weisdayspabackend.entity.Employee;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentEmployee {

    @Id
    @GeneratedValue
    private Long appointmentEmployeeId;

    @ManyToOne
    @JoinColumn(name = "appointmentId", nullable = false)
    private Appointment appointment;

    @ManyToOne
    @JoinColumn(name = "employeeId", nullable = false)
    private Employee employee;
}
