package org.animapolis.healthcare.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public class EntityBase {

    @Column(unique = true, nullable = false)
    private String resourceId;

    @Column(updatable = false, nullable = false)
    private LocalDateTime creationDate;

    @Column(nullable = false)
    private LocalDateTime lastUpdatedDate;
}
