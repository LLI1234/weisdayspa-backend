package org.example.weisdayspabackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeTreatment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeTreatmentId;

    @ManyToOne
    @JoinColumn(name = "employeeId", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "treatmentId", nullable = false)
    private Treatment treatment;
}
