package com.animapolis.employee.repository;

import com.animapolis.employee.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, EntityManagerRepository {

    Optional<Employee> findByResourceId(String resourceId);

    @Query("SELECT employee.id FROM Employee employee WHERE employee.resourceId = :resourceId")
    Optional<Long> findIdByResourceId(@Param("resourceId") String resourceId);

    void deleteByResourceId(String resourceId);
}
