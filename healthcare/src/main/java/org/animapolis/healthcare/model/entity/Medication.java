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
public class Medication extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String form;

    private String description;

    @Column(nullable = false)
    private LocalDateTime expirationDate;

    @OneToMany(mappedBy = "medication", cascade = CascadeType.REMOVE)
    private List<MedicationPrescription> medicationPrescriptions;

    @OneToMany(mappedBy = "medication", cascade = CascadeType.REMOVE)
    private List<MedicationAdministration> medicationAdministrations;
}
