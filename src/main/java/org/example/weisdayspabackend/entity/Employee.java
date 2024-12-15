package org.example.weisdayspabackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.weisdayspabackend.entity.junctiontable.AppointmentEmployee;
import org.example.weisdayspabackend.entity.junctiontable.EmployeeSchedule;
import org.example.weisdayspabackend.entity.junctiontable.EmployeeTreatment;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Employee {
    @Id
    @GeneratedValue
    private Long employeeId;
    private String first_name;
    private String last_name;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<EmployeeTreatment> employeeTreatments;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<EmployeeSchedule> employeeSchedules;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<AppointmentEmployee> appointmentEmployees;
}
