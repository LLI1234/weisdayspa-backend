package org.example.weisdayspabackend.controller;

import org.example.weisdayspabackend.entity.Employee;
import org.example.weisdayspabackend.entity.junctiontable.EmployeeTreatment;
import org.example.weisdayspabackend.entity.Treatment;
import org.example.weisdayspabackend.service.interfaces.EmployeeTreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee-treatments")
public class EmployeeTreatmentController {

    private final EmployeeTreatmentService employeeTreatmentService;

    @Autowired
    public EmployeeTreatmentController(EmployeeTreatmentService employeeTreatmentService) {
        this.employeeTreatmentService = employeeTreatmentService;
    }

    // Link employee to treatment that they can perform
    @PostMapping
    public ResponseEntity<EmployeeTreatment> assignEmployeeToTreatment(@RequestBody Map<String, Long> request) {
        Long employeeId = request.get("employeeId");
        Long treatmentId = request.get("treatmentId");
        EmployeeTreatment employeeTreatment = employeeTreatmentService.assignEmployeeToTreatment(employeeId, treatmentId);
        return new ResponseEntity<>(employeeTreatment, HttpStatus.CREATED);
    }

    // Return a list of employees that can perform a certain treatment
    @GetMapping("/treatment/{treatmentId}")
    public ResponseEntity<List<Employee>> getEmployeesByTreatment(@PathVariable Long treatmentId) {
        return new ResponseEntity<>(employeeTreatmentService.getEmployeesByTreatment(treatmentId), HttpStatus.OK);
    }

    // Return a list of treatments a certain employee can perform
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Treatment>> getTreatmentsByEmployee(@PathVariable Long employeeId) {
        return new ResponseEntity<>(employeeTreatmentService.getTreatmentsByEmployee(employeeId), HttpStatus.OK);
    }

    // Remove a treatment from an employee
    @DeleteMapping("/employees/{employeeId}/treatments/{treatmentId}")
    public ResponseEntity<Void> removeEmployeeFromTreatment(@PathVariable Long employeeId, @PathVariable Long treatmentId) {
        employeeTreatmentService.removeEmployeeFromTreatment(employeeId, treatmentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
