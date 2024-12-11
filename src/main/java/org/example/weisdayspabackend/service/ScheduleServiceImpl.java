package org.example.weisdayspabackend.service;

import org.example.weisdayspabackend.entity.Schedule;
import org.example.weisdayspabackend.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public Schedule createSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public List<Schedule> findAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public Optional<Schedule> findSchedule(Long scheduleId) {
        return scheduleRepository.findById(scheduleId);
    }

    @Override
    public Schedule updateSchedule(Long scheduleId, Schedule updatedSchedule) {
        Schedule existingSchedule = findSchedule(scheduleId)
                .orElseThrow(() -> new RuntimeException("Schedule with id: " +scheduleId + " not found"));

        if(Objects.nonNull(updatedSchedule.getRecurs())) {
            existingSchedule.setRecurs(updatedSchedule.getRecurs());
        }
        if(Objects.nonNull(updatedSchedule.getDayOfWeek())) {
            existingSchedule.setDayOfWeek(updatedSchedule.getDayOfWeek());
        }
        if(Objects.nonNull(updatedSchedule.getStartTime())) {
            existingSchedule.setStartTime(updatedSchedule.getStartTime());
        }
        if(Objects.nonNull(updatedSchedule.getEndTime())) {
            existingSchedule.setEndTime(updatedSchedule.getEndTime());
        }

        return scheduleRepository.save(existingSchedule);
    }

    @Override
    public void deleteSchedule(Long scheduleId) {
        if(scheduleRepository.existsById(scheduleId)) {
            scheduleRepository.deleteById(scheduleId);
        }
        else {
            throw new RuntimeException("Schedule with id: " +scheduleId + " not found");
        }
    }
}
