package org.example.weisdayspabackend.service;

import org.example.weisdayspabackend.entity.TreatmentDuration;
import org.example.weisdayspabackend.repository.TreatmentDurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TreatmentDurationServiceImpl implements TreatmentDurationService{

    private final TreatmentDurationRepository duration_repository;

    @Autowired
    public TreatmentDurationServiceImpl(TreatmentDurationRepository treatmentDurationRepository) {
        this.duration_repository = treatmentDurationRepository;
    }

    @Override
    public TreatmentDuration createDuration(TreatmentDuration duration) {
        return duration_repository.save(duration);
    }

    @Override
    public List<TreatmentDuration> getDurationsByTreatment(Long treatmentId) {
        return duration_repository.findByTreatment_TreatmentId(treatmentId);
    }

    @Override
    public TreatmentDuration updateDuration(Long durationId, TreatmentDuration updated_duration) {
        TreatmentDuration existing_duration = duration_repository.findById(durationId)
                .orElseThrow(() -> new RuntimeException("Duration with ID " + durationId + " not found"));
        if(updated_duration.getDuration() != null) {
            existing_duration.setDuration(updated_duration.getDuration());
        }
        if(updated_duration.getPrice() != null) {
            existing_duration.setPrice(updated_duration.getPrice());
        }

        return duration_repository.save(existing_duration);
    }

    @Override
    public void deleteDuration(Long durationId) {
        if(!duration_repository.existsById(durationId)) {
            throw new RuntimeException("Duration with ID " + durationId + " not found");
        }
        duration_repository.deleteById(durationId);
    }
}
