package org.animapolis.healthcare.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(indexes = @Index(columnList = "resourceId"))
public class Prescription extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String instruction;

    @Column(nullable = false)
    private boolean isCompleted;

    private LocalDateTime authoredDate;

    private String animalResourceId;

    @OneToMany(mappedBy = "prescription", cascade = CascadeType.REMOVE)
    private List<MedicationPrescription> medicationPrescriptions;

    @OneToMany(mappedBy = "prescription", cascade = CascadeType.REMOVE)
    private List<MedicationAdministration> medicationAdministrations;
}
