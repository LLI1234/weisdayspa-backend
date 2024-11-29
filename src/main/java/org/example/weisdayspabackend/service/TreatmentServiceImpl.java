package org.example.weisdayspabackend.service;

import org.example.weisdayspabackend.entity.Treatment;
import org.example.weisdayspabackend.repository.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TreatmentServiceImpl implements TreatmentService {

    private final TreatmentRepository treatmentRepository;

    @Autowired
    public TreatmentServiceImpl(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    @Override
    public Treatment createTreatment(Treatment treatment) {
        return treatmentRepository.save(treatment);
    }

    @Override
    public Optional<Treatment> getTreatment(Long treatment_id) {
        return treatmentRepository.findById(treatment_id);
    }

    @Override
    public List<Treatment> getAllTreatments() {
        return (List<Treatment>) treatmentRepository.findAll();
    }

    @Override
    public Treatment updateTreatment(Long treatment_id, Treatment updated_treatment) {
        Treatment existing_treatment = getTreatment(treatment_id)
                .orElseThrow(() -> new RuntimeException("Treatment with id " + treatment_id + " not found"));

        // Update name if a new name is provided
        if (Objects.nonNull(updated_treatment.getName()) && !updated_treatment.getName().isEmpty()) {
            existing_treatment.setName(updated_treatment.getName());
        }

        // Update description if a new description is provided
        if (Objects.nonNull(updated_treatment.getDescription()) && !updated_treatment.getDescription().isEmpty()) {
            existing_treatment.setDescription(updated_treatment.getDescription());
        }

        // Update durations if a new list is provided
//        if (Objects.nonNull(updated_treatment.getDurations()) && !updated_treatment.getDurations().isEmpty()) {
//            // Clear existing durations and add updated ones
//            existing_treatment.getDurations().clear();
//            existing_treatment.getDurations().addAll(updated_treatment.getDurations());
//
//            // Ensure the bidirectional relationship is maintained, if applicable
//            existing_treatment.getDurations().forEach(duration -> duration.setTreatment(existing_treatment));
//        }

        return treatmentRepository.save(existing_treatment);
    }

    @Override
    public void deleteTreatment(Long treatment_id) {
        if(treatmentRepository.existsById(treatment_id)) {
            treatmentRepository.deleteById(treatment_id);
        }
        else {
            throw new RuntimeException("Treatment with id " + treatment_id + " not found");
        }
    }
}
