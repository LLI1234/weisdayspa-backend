package org.example.weisdayspabackend.service;

import org.example.weisdayspabackend.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleService {
    Schedule createSchedule(Schedule schedule);
    List<Schedule> findAllSchedules();
    Optional<Schedule> findSchedule(Long scheduleId);
    Schedule updateSchedule(Long scheduleId, Schedule updatedSchedule);
    void deleteSchedule(Long scheduleId);
}
