package org.example.weisdayspabackend.entity.junctiontable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.weisdayspabackend.entity.Employee;
import org.example.weisdayspabackend.entity.Schedule;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSchedule {

    @Id
    @GeneratedValue
    private Long employeeScheduleId;

    @ManyToOne
    @JoinColumn(name = "scheduleId", nullable = false)
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "employeeId", nullable = false)
    private Employee employee;
}
