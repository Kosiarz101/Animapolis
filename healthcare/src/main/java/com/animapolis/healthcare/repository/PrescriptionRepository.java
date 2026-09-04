package com.animapolis.healthcare.repository;

import com.animapolis.healthcare.model.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long>, EntityManagerRepository {

    Optional<Prescription> findByResourceId(UUID resourceId);

    @Query("SELECT prescription.id FROM Prescription prescription WHERE prescription.resourceId = :resourceId")
    Optional<Long> findIdByResourceId(@Param("resourceId") UUID resourceId);
}
