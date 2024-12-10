package org.example.weisdayspabackend.service;

import org.example.weisdayspabackend.entity.Employee;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee createEmployee(Employee employee);
    Optional<Employee> findEmployee(Long employeeId);
    List<Employee> findAllEmployees();
    Employee updateEmployee(Long employeeId, Employee updated_employee);
    void deleteEmployee(Long employeeId);
}
