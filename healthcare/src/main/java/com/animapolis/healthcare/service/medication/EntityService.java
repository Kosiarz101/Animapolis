package com.animapolis.healthcare.service.medication;

import com.animapolis.healthcare.model.dto.request.DtoRequestBase;
import com.animapolis.healthcare.model.dto.response.DtoResponseBase;

import java.util.List;
import java.util.UUID;

public interface EntityService<REQ extends DtoRequestBase, RES extends DtoResponseBase> {

    RES create(REQ dto);

    RES get(UUID resourceId);

    List<RES> getAll();

    RES update(UUID resourceId, REQ dto);

    void delete(UUID resourceId);
}
