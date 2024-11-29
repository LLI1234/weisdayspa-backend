package org.example.weisdayspabackend.service;

import org.example.weisdayspabackend.entity.TreatmentDuration;

import java.util.List;

public interface TreatmentDurationService {
    TreatmentDuration createDuration(TreatmentDuration duration);
    List<TreatmentDuration> getDurationsByTreatment(Long treatment_id);
    TreatmentDuration updateDuration(Long treatment_id, TreatmentDuration updated_duration);
    void deleteDuration(Long duration_id);
}