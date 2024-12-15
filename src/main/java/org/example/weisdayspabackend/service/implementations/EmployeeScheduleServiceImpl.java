package org.example.weisdayspabackend.service.implementations;

import org.example.weisdayspabackend.entity.Employee;
import org.example.weisdayspabackend.entity.Schedule;
import org.example.weisdayspabackend.entity.junctiontable.EmployeeSchedule;
import org.example.weisdayspabackend.repository.EmployeeRepository;
import org.example.weisdayspabackend.repository.EmployeeScheduleRepository;
import org.example.weisdayspabackend.repository.ScheduleRepository;
import org.example.weisdayspabackend.service.interfaces.EmployeeScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeScheduleServiceImpl implements EmployeeScheduleService {

    private final EmployeeScheduleRepository employeeScheduleRepository;
    private final ScheduleRepository scheduleRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeScheduleServiceImpl(EmployeeScheduleRepository employeeScheduleRepository, ScheduleRepository scheduleRepository, EmployeeRepository employeeRepository) {
        this.employeeScheduleRepository = employeeScheduleRepository;
        this.scheduleRepository = scheduleRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeSchedule assignScheduleToEmployee(Long scheduleId, Long employeeId) {
        // Fetch the Employee and Schedule entities from the database
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("Schedule not found with id: " + scheduleId));

        // Create the EmployeeSchedule entity and set the relations
        EmployeeSchedule employeeSchedule = new EmployeeSchedule();
        employeeSchedule.setEmployee(employee);
        employeeSchedule.setSchedule(schedule);

        // Save and return the EmployeeSchedule entity
        return employeeScheduleRepository.save(employeeSchedule);
    }

    @Override
    public List<Schedule> getSchedulesByEmployee(Long employeeId) {
        List<EmployeeSchedule> employeeSchedules = employeeScheduleRepository.findByEmployee_EmployeeId(employeeId);
        return employeeSchedules.stream()
                .map(EmployeeSchedule::getSchedule)
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> getEmployeesBySchedule(Long scheduleId) {
        List<EmployeeSchedule> employeeSchedules = employeeScheduleRepository.findBySchedule_ScheduleId(scheduleId);
        return employeeSchedules.stream()
                .map(EmployeeSchedule::getEmployee)
                .collect(Collectors.toList());
    }

    @Override
    public void removeScheduleFromEmployee(Long scheduleId, Long employeeId) {
        EmployeeSchedule employeeSchedule = employeeScheduleRepository.findByEmployee_EmployeeId_AndSchedule_ScheduleId(employeeId, scheduleId)
                .orElseThrow(() -> new RuntimeException("Employee-Schedule relation not found"));

        employeeScheduleRepository.delete(employeeSchedule);
    }
}
