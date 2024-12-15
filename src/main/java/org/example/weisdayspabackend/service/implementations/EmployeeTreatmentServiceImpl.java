package org.example.weisdayspabackend.service.implementations;

import org.example.weisdayspabackend.entity.Employee;
import org.example.weisdayspabackend.entity.junctiontable.EmployeeTreatment;
import org.example.weisdayspabackend.entity.Treatment;
import org.example.weisdayspabackend.repository.EmployeeRepository;
import org.example.weisdayspabackend.repository.EmployeeTreatmentRepository;
import org.example.weisdayspabackend.repository.TreatmentRepository;
import org.example.weisdayspabackend.service.interfaces.EmployeeTreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeTreatmentServiceImpl implements EmployeeTreatmentService {

    private final EmployeeTreatmentRepository employeeTreatmentRepository;
    private final EmployeeRepository employeeRepository;
    private final TreatmentRepository treatmentRepository;

    @Autowired
    public EmployeeTreatmentServiceImpl(
            EmployeeTreatmentRepository employeeTreatmentRepository,
            EmployeeRepository employeeRepository,
            TreatmentRepository treatmentRepository
    ) {
        this.employeeTreatmentRepository = employeeTreatmentRepository;
        this.employeeRepository = employeeRepository;
        this.treatmentRepository = treatmentRepository;
    }

    @Override
    public EmployeeTreatment assignEmployeeToTreatment(Long employeeId, Long treatmentId) {
        // Fetch the Employee and Treatment entities from the database
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));
        Treatment treatment = treatmentRepository.findById(treatmentId)
                .orElseThrow(() -> new RuntimeException("Treatment not found with id: " + treatmentId));

        // Create the EmployeeTreatment entity and set the relations
        EmployeeTreatment employeeTreatment = new EmployeeTreatment();
        employeeTreatment.setEmployee(employee);
        employeeTreatment.setTreatment(treatment);

        // Save and return the EmployeeTreatment entity
        return employeeTreatmentRepository.save(employeeTreatment);
    }


    @Override
    public List<Employee> getEmployeesByTreatment(Long treatmentId) {
        List<EmployeeTreatment> employeeTreatments = employeeTreatmentRepository.findByTreatment_TreatmentId(treatmentId);
        return employeeTreatments.stream()
                .map(EmployeeTreatment::getEmployee) // Extract the Employee from each EmployeeTreatment
                .collect(Collectors.toList()); // Collect as a List<Treatment>
    }

    @Override
    public List<Treatment> getTreatmentsByEmployee(Long employeeId) {
        List<EmployeeTreatment> employeeTreatments = employeeTreatmentRepository.findByEmployee_EmployeeId(employeeId);
        return employeeTreatments.stream()
                .map(EmployeeTreatment::getTreatment) // Extract the Treatment from each EmployeeTreatment
                .collect(Collectors.toList()); // Collect as a List<Treatment>
    }


    @Override
    public void removeEmployeeFromTreatment(Long employeeId, Long treatmentId) {
        EmployeeTreatment employeeTreatment = employeeTreatmentRepository.findByEmployee_EmployeeId_AndTreatment_TreatmentId(employeeId, treatmentId)
                .orElseThrow(() -> new RuntimeException("Employee-Treatment relation not found"));

        employeeTreatmentRepository.delete(employeeTreatment);
    }
}
