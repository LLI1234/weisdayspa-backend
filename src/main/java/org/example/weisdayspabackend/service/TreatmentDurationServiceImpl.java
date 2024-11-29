package org.example.weisdayspabackend.service;

import org.example.weisdayspabackend.entity.Treatment;
import org.example.weisdayspabackend.entity.TreatmentDuration;
import org.example.weisdayspabackend.repository.TreatmentDurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TreatmentDurationServiceImpl implements TreatmentDurationService{

    private final TreatmentDurationRepository durationRepository;

    @Autowired
    public TreatmentDurationServiceImpl(TreatmentDurationRepository treatmentDurationRepository) {
        this.durationRepository = treatmentDurationRepository;
    }

    @Override
    public TreatmentDuration createDuration(TreatmentDuration duration) {
        return durationRepository.save(duration);
    }

    @Override
    public List<TreatmentDuration> getDurationsByTreatment(Long treatment_id) {
        return durationRepository.findByTreatmentId(treatment_id);
    }

    @Override
    public TreatmentDuration updateDuration(Long duration_id, TreatmentDuration updated_duration) {
        TreatmentDuration existing_duration = durationRepository.findById(duration_id)
                .orElseThrow(() -> new RuntimeException("Duration with ID " + duration_id + " not found"));
        if(updated_duration.getDuration() != null) {
            existing_duration.setDuration(updated_duration.getDuration());
        }
        if(updated_duration.getPrice() != null) {
            existing_duration.setPrice(updated_duration.getPrice());
        }

        return durationRepository.save(existing_duration);
    }

    @Override
    public void deleteDuration(Long duration_id) {
        if(!durationRepository.existsById(duration_id)) {
            throw new RuntimeException("Duration with ID " + duration_id + " not found");
        }
        durationRepository.deleteById(duration_id);
    }
}
