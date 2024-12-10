package org.example.weisdayspabackend.service;

import org.example.weisdayspabackend.entity.Employee;
import org.example.weisdayspabackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> findEmployee(Long employeeId) {
        return employeeRepository.findById(employeeId);
    }

    @Override
    public List<Employee> findAllEmployees() {
        return (List<Employee>) employeeRepository.findAll();
    }

    @Override
    public Employee updateEmployee(Long employeeId, Employee updated_employee) {
        Employee existing_employee = findEmployee(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee with id " + employeeId + " not found"));

        // Update first name if a new first name is provided
        if(Objects.nonNull(updated_employee.getFirst_name()) && !updated_employee.getFirst_name().isEmpty()) {
            existing_employee.setFirst_name(updated_employee.getFirst_name());
        }
        // Update last name if a new last name is provided
        if(Objects.nonNull(updated_employee.getLast_name()) && !updated_employee.getLast_name().isEmpty()) {
            existing_employee.setLast_name(updated_employee.getLast_name());
        }

        return employeeRepository.save(existing_employee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        if(employeeRepository.existsById(employeeId)) {
            employeeRepository.deleteById(employeeId);
        }
        else {
            throw new RuntimeException("Employee with id " + employeeId + " not found");
        }
    }
}
