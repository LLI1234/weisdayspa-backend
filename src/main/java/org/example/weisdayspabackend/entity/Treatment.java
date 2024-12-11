package org.example.weisdayspabackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Treatment {

    @Id
    @GeneratedValue
    private Long treatmentId;
    private String name;
    private String description;

    @ElementCollection
    @CollectionTable(name = "treatment_duration_price", joinColumns = @JoinColumn(name = "treatment_id"))
    @MapKeyColumn(name = "duration") // Key column for the duration (e.g., 30, 60 mins)
    @Column(name = "price") // Value column for the price
    private Map<Integer, BigDecimal> durationsAndPrices;

    @OneToMany(mappedBy = "treatment", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<EmployeeTreatment> employeeTreatments;
}
