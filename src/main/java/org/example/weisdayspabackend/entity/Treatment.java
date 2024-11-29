package org.example.weisdayspabackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Treatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long treatment_id;
    private String name;
    private String description;

//    @OneToMany(mappedBy = "treatment", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<TreatmentDuration> durations = new ArrayList<>();
}
