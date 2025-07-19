package org.animapolis.healthcare.repository;

import org.animapolis.healthcare.model.entity.MedicationAdministration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicationAdministrationRepository extends JpaRepository<MedicationAdministration, Long>, EntityManagerRepository {

    Optional<MedicationAdministration> findByResourceId(String resourceId);

    @Query("SELECT ma.id FROM MedicationAdministration ma WHERE ma.resourceId = :resourceId")
    Optional<Long> findIdByResourceId(@Param("resourceId") String resourceId);
}
