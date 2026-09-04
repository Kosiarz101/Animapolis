package com.animapolis.healthcare.repository;

import com.animapolis.healthcare.model.entity.MedicationAdministration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MedicationAdministrationRepository extends JpaRepository<MedicationAdministration, Long>, EntityManagerRepository {

    Optional<MedicationAdministration> findByResourceId(UUID resourceId);

    @Query("SELECT ma.id FROM MedicationAdministration ma WHERE ma.resourceId = :resourceId")
    Optional<Long> findIdByResourceId(@Param("resourceId") UUID resourceId);
}
