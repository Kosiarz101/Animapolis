package org.animapolis.healthcare.repository;

import org.animapolis.healthcare.model.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long>, EntityManagerRepository {

    Optional<Medication> findByResourceId(String resourceId);

    @Query("SELECT medication.id FROM Medication medication WHERE medication.resourceId = :resourceId")
    Optional<Long> findIdByResourceId(@Param("resourceId") String resourceId);
}
