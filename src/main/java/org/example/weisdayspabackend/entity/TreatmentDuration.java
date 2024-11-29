package org.example.weisdayspabackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class TreatmentDuration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long duration_id;
    private Integer duration; // in minutes
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "treatment_id", nullable = false)
    private Treatment treatment;
}
