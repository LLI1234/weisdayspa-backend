package org.example.weisdayspabackend.repository;

import org.example.weisdayspabackend.entity.junctiontable.AppointmentEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentEmployeeRepository extends JpaRepository<AppointmentEmployee, Long> {
}
