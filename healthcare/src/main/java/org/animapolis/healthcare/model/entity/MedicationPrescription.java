package org.animapolis.healthcare.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@Entity
@Table(indexes = @Index(columnList = "resourceId"))
public class MedicationPrescription extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double dosageValue;

    @Column(nullable = false)
    private String dosageUnit;

    private String dosageInstruction;

    @ManyToOne
    @JoinColumn(name = "prescription_id", referencedColumnName = "id", nullable = false)
    private Prescription prescription;

    @ManyToOne
    @JoinColumn(name = "medication_id", referencedColumnName = "id", nullable = false)
    private Medication medication;
}
