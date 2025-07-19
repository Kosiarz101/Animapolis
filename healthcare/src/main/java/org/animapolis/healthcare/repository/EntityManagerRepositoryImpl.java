package org.animapolis.healthcare.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EntityManagerRepositoryImpl implements EntityManagerRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void refresh(Object object) {
        entityManager.refresh(object);
    }
}
