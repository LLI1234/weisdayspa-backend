package org.example.weisdayspabackend.service;

import org.example.weisdayspabackend.entity.TreatmentDuration;

import java.util.List;

public interface TreatmentDurationService {
    TreatmentDuration createDuration(TreatmentDuration duration);
    List<TreatmentDuration> getDurationsByTreatment(Long treatmentId);
    TreatmentDuration updateDuration(Long treatmentId, TreatmentDuration updated_duration);
    void deleteDuration(Long durationId);
}