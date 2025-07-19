package org.animapolis.healthcare.repository;

import org.animapolis.healthcare.model.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long>, EntityManagerRepository {

    Optional<Prescription> findByResourceId(String resourceId);

    @Query("SELECT prescription.id FROM Prescription prescription WHERE prescription.resourceId = :resourceId")
    Optional<Long> findIdByResourceId(@Param("resourceId") String resourceId);
}
