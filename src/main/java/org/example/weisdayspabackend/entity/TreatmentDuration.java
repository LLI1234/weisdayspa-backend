package org.example.weisdayspabackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class TreatmentDuration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long durationId;
    private Integer duration; // in minutes
    private BigDecimal price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "treatmentId", nullable = false)
    private Treatment treatment;
}
