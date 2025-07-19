package org.animapolis.healthcare.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(indexes = @Index(columnList = "resourceId"))
public class MedicationAdministration extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime administrationDate;

    @Column(nullable = false)
    private Double dosageValue;

    @Column(nullable = false)
    private String dosageUnit;

    @ManyToOne
    @JoinColumn(name = "prescription_id", referencedColumnName = "id", nullable = false)
    private Prescription prescription;

    @ManyToOne
    @JoinColumn(name = "medication_id", referencedColumnName = "id", nullable = false)
    private Medication medication;
}
