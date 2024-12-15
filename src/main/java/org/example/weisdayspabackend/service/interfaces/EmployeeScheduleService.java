package org.example.weisdayspabackend.service.interfaces;

import org.example.weisdayspabackend.entity.Schedule;
import org.example.weisdayspabackend.entity.Employee;
import org.example.weisdayspabackend.entity.junctiontable.EmployeeSchedule;

import java.util.List;

public interface EmployeeScheduleService {
    EmployeeSchedule assignScheduleToEmployee(Long scheduleId, Long employeeId);
    List<Schedule> getSchedulesByEmployee(Long employeeId);
    List<Employee> getEmployeesBySchedule(Long scheduleId);
    void removeScheduleFromEmployee(Long scheduleId, Long employeeId);
}
