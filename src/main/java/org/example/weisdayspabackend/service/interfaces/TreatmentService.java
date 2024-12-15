package org.example.weisdayspabackend.service.interfaces;

import org.example.weisdayspabackend.entity.Treatment;
import java.util.List;
import java.util.Optional;

public interface TreatmentService {
    Treatment createTreatment(Treatment treatment);
    Optional<Treatment> getTreatment(Long treatmentId);
    List<Treatment> getAllTreatments();
    Treatment updateTreatment(Long treatmentId, Treatment updated_treatment);
    void deleteTreatment(Long treatmentId);
}
