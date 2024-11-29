package org.example.weisdayspabackend.service;

import org.example.weisdayspabackend.entity.Employee;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee createEmployee(Employee employee);
    Optional<Employee> findEmployee(Long employee_id);
    List<Employee> findAllEmployees();
    Employee updateEmployee(Long employee_id, Employee updated_employee);
    void deleteEmployee(Long employee_id);
}
