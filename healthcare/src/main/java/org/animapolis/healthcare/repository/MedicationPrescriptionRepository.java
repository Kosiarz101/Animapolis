package org.animapolis.healthcare.repository;

import org.animapolis.healthcare.model.entity.MedicationPrescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicationPrescriptionRepository extends JpaRepository<MedicationPrescription, Long>, EntityManagerRepository {

    Optional<MedicationPrescription> findByResourceId(String resourceId);

    @Query("SELECT mp.id FROM MedicationPrescription mp WHERE mp.resourceId = :resourceId")
    Optional<Long> findIdByResourceId(@Param("resourceId") String resourceId);
}
