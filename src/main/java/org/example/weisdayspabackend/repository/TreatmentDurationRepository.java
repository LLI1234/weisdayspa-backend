package org.example.weisdayspabackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.weisdayspabackend.entity.TreatmentDuration;

import java.util.List;

@Repository
public interface TreatmentDurationRepository extends JpaRepository<TreatmentDuration, Long> {
    List<TreatmentDuration> findByTreatment_TreatmentId(Long treatmentId);
}
