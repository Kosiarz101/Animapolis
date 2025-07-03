package com.animapolis.employee.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EntityManagerRepositoryImpl implements EntityManagerRepository {

    private final EntityManager entityManager;

    @Override
    public void refresh(Object object) {
        entityManager.refresh(object);
    }
}
