package org.example.weisdayspabackend.entity.junctiontable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.weisdayspabackend.entity.Employee;
import org.example.weisdayspabackend.entity.Treatment;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeTreatment {

    @Id
    @GeneratedValue
    private Long employeeTreatmentId;

    @ManyToOne
    @JoinColumn(name = "employeeId", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "treatmentId", nullable = false)
    private Treatment treatment;
}
