package com.animapolis.employee.repository;

import com.animapolis.employee.model.entity.EmploymentContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmploymentContractRepository extends JpaRepository<EmploymentContract, Long>, EntityManagerRepository {

    Optional<EmploymentContract> findByResourceId(String resourceId);

    @Query("SELECT e.id FROM EmploymentContract e WHERE e.resourceId = :resourceId")
    Optional<Long> findIdByResourceId(@Param("resourceId") String resourceId);

    @Query("SELECT ec.id FROM EmploymentContract ec JOIN ec.employee ee WHERE ee.resourceId = :employeeId")
    Optional<Long> findByEmployeeId(@Param("employeeId") Long employeeId);

    boolean existsByEmployeeId(Long employeeId);

    void deleteByResourceId(String resourceId);
}
