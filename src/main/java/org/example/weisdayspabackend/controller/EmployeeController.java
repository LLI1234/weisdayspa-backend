package org.example.weisdayspabackend.controller;

import org.example.weisdayspabackend.entity.Employee;
import org.example.weisdayspabackend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employee_service;

    @Autowired
    public EmployeeController(EmployeeService employee_service) {
        this.employee_service = employee_service;
    }

    // Create operation
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee new_employee = employee_service.createEmployee(employee);
        return new ResponseEntity<>(new_employee, HttpStatus.CREATED);
    }

    // Read operation - get employee by id
    @GetMapping("/{employee_id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable long employee_id) {
        return employee_service.findEmployee(employee_id)
                .map(employee -> new ResponseEntity<>(employee, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Read operation - get all employees
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(employee_service.findAllEmployees(), HttpStatus.OK);
    }

    // Update operation
    @PutMapping("/{employee_id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long employee_id, @RequestBody Employee updated_employee) {
        try {
            Employee employee = employee_service.updateEmployee(employee_id, updated_employee);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
        catch(RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete operation
    @DeleteMapping("/{employee_id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable long employee_id) {
        try {
            employee_service.deleteEmployee(employee_id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
