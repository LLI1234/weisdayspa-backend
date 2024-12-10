package org.example.weisdayspabackend.repository;

import org.example.weisdayspabackend.entity.EmployeeTreatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeTreatmentRepository extends JpaRepository<EmployeeTreatment, Long> {
    List<EmployeeTreatment> findByTreatment_TreatmentId(Long treatmentId);
    List<EmployeeTreatment> findByEmployee_EmployeeId(Long employeeId);
    Optional<EmployeeTreatment> findByEmployee_EmployeeId_AndTreatment_TreatmentId(Long employeeId, Long treatmentId);
}
