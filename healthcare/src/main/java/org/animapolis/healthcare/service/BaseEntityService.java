package org.animapolis.healthcare.service;

import org.animapolis.healthcare.model.entity.EntityBase;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class BaseEntityService {

    public void prepareForCreation(EntityBase entityBase) {
        LocalDateTime now = LocalDateTime.now();
        entityBase.setCreationDate(now);
        entityBase.setLastUpdatedDate(now);
        entityBase.setResourceId(UUID.randomUUID().toString());
    }

    public void prepareForUpdate(EntityBase entityBase) {
        entityBase.setLastUpdatedDate(LocalDateTime.now());
    }
}
