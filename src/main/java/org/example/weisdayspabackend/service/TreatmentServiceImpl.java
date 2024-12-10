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
    public Optional<Treatment> getTreatment(Long treatmentId) {
        return treatmentRepository.findById(treatmentId);
    }

    @Override
    public List<Treatment> getAllTreatments() {
        return treatmentRepository.findAll();
    }

    @Override
    public Treatment updateTreatment(Long treatmentId, Treatment updatedTreatment) {
        Treatment existingTreatment = getTreatment(treatmentId)
                .orElseThrow(() -> new RuntimeException("Treatment with id " + treatmentId + " not found"));

        // Update name if a new name is provided
        if (Objects.nonNull(updatedTreatment.getName()) && !updatedTreatment.getName().isEmpty()) {
            existingTreatment.setName(updatedTreatment.getName());
        }

        // Update description if a new description is provided
        if (Objects.nonNull(updatedTreatment.getDescription()) && !updatedTreatment.getDescription().isEmpty()) {
            existingTreatment.setDescription(updatedTreatment.getDescription());
        }

        if (Objects.nonNull(updatedTreatment.getDurationsAndPrices()) && !updatedTreatment.getDurationsAndPrices().isEmpty()) {
            existingTreatment.setDurationsAndPrices(updatedTreatment.getDurationsAndPrices());
        }

        return treatmentRepository.save(existingTreatment);
    }

    @Override
    public void deleteTreatment(Long treatmentId) {
        if(treatmentRepository.existsById(treatmentId)) {
            treatmentRepository.deleteById(treatmentId);
        }
        else {
            throw new RuntimeException("Treatment with id " + treatmentId + " not found");
        }
    }
}
