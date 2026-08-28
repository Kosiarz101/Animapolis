package com.animapolis.healthcare.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Prescription extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private UUID authorResourceId;

    @Column(nullable = false)
    private UUID animalResourceId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean isCompleted;

    @Column(nullable = false)
    private LocalDateTime authoredDate;

    private String instruction;

    @OneToMany(mappedBy = "prescription", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<MedicationPrescription> medicationPrescriptions;

    @OneToMany(mappedBy = "prescription", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<MedicationAdministration> medicationAdministrations;
}
