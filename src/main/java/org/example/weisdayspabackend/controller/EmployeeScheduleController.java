package org.example.weisdayspabackend.controller;

import org.example.weisdayspabackend.entity.Employee;
import org.example.weisdayspabackend.entity.Schedule;
import org.example.weisdayspabackend.entity.junctiontable.EmployeeSchedule;
import org.example.weisdayspabackend.service.interfaces.EmployeeScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee-schedules")
public class EmployeeScheduleController {

    private final EmployeeScheduleService employeeScheduleService;

    @Autowired
    public EmployeeScheduleController(EmployeeScheduleService employeeScheduleService) {
        this.employeeScheduleService = employeeScheduleService;
    }

    @PostMapping
    public ResponseEntity<EmployeeSchedule> assignScheduleToEmployee(@RequestBody Map<String, Long> request) {
        Long employeeId = request.get("employeeId");
        Long scheduleId = request.get("scheduleId");
        EmployeeSchedule employeeSchedule = employeeScheduleService.assignScheduleToEmployee(scheduleId, employeeId);
        return new ResponseEntity<>(employeeSchedule, HttpStatus.CREATED);
    }

    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<List<Schedule>> getSchedulesByEmployee(@PathVariable Long employeeId) {
        return new ResponseEntity<>(employeeScheduleService.getSchedulesByEmployee(employeeId), HttpStatus.OK);
    }

    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<List<Employee>> getEmployeesBySchedule(@PathVariable Long scheduleId) {
        return new ResponseEntity<>(employeeScheduleService.getEmployeesBySchedule(scheduleId), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> removeScheduleFromEmployee(@RequestParam Long employeeId, @RequestParam Long scheduleId) {
        employeeScheduleService.removeScheduleFromEmployee(scheduleId, employeeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
