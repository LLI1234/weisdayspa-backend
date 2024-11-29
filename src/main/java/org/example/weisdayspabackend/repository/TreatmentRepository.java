package org.example.weisdayspabackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.weisdayspabackend.entity.Treatment;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
}
