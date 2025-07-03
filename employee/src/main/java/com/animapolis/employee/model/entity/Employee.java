package com.animapolis.employee.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(indexes = @Index(columnList = "resourceId"))
public class Employee extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(length = 11)
    private String pesel;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(length = 15)
    private String phoneNumber;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.REMOVE)
    private EmploymentContract contract;
}
