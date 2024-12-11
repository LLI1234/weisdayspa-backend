package org.example.weisdayspabackend.repository;

import org.example.weisdayspabackend.entity.junctiontable.EmployeeSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeScheduleRepository extends JpaRepository<EmployeeSchedule, Long> {
    List<EmployeeSchedule> findByEmployee_EmployeeId(Long employeeId);
    List<EmployeeSchedule> findBySchedule_ScheduleId(Long scheduleId);
    Optional<EmployeeSchedule> findByEmployee_EmployeeId_AndSchedule_ScheduleId(Long employeeId, Long scheduleId);
}
