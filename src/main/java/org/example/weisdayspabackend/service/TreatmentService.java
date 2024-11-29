package org.example.weisdayspabackend.service;

import org.example.weisdayspabackend.entity.Treatment;
import java.util.List;
import java.util.Optional;

public interface TreatmentService {
    Treatment createTreatment(Treatment treatment);
    Optional<Treatment> getTreatment(Long treatment_id);
    List<Treatment> getAllTreatments();
    Treatment updateTreatment(Long treatment_id, Treatment updated_treatment);
    void deleteTreatment(Long treatment_id);
}
