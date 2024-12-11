package org.example.weisdayspabackend.service;

import org.example.weisdayspabackend.entity.Employee;
import org.example.weisdayspabackend.entity.EmployeeTreatment;
import org.example.weisdayspabackend.entity.Treatment;

import java.util.List;

public interface EmployeeTreatmentService {

    EmployeeTreatment assignEmployeeToTreatment(Long employeeId, Long treatmentId);

    List<Employee> getEmployeesByTreatment(Long treatmentId);

    List<Treatment> getTreatmentsByEmployee(Long employeeId);

    void removeEmployeeFromTreatment(Long employeeId, Long treatmentId);
}
