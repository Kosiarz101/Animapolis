package com.animapolis.employee.model.entity;

import com.animapolis.employee.model.type.ContractType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(indexes = @Index(columnList = "resourceId"))
public class EmploymentContract extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer salary;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ContractType type;

    @Column(nullable = false)
    private LocalDate employmentStartDate;

    private LocalDate employmentEndDate;

    @OneToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id", nullable = false)
    private Employee employee;
}
