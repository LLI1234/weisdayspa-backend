package org.example.weisdayspabackend.service;

import org.example.weisdayspabackend.entity.EmployeeTreatment;

import java.util.List;

public interface EmployeeTreatmentService {

    EmployeeTreatment assignEmployeeToTreatment(Long employeeId, Long treatmentId);

    List<EmployeeTreatment> getEmployeesByTreatment(Long treatmentId);

    List<EmployeeTreatment> getTreatmentsByEmployee(Long employeeId);

    void removeEmployeeFromTreatment(Long employeeId, Long treatmentId);
}
