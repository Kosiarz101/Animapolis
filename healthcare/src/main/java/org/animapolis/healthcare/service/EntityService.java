package org.animapolis.healthcare.service;

import org.animapolis.healthcare.model.dto.request.DtoRequestBase;
import org.animapolis.healthcare.model.dto.response.DtoResponseBase;

import java.util.List;

public interface EntityService<REQ extends DtoRequestBase, RES extends DtoResponseBase> {

    RES create(REQ dto);

    RES get(String resourceId);

    List<RES> getAll();

    RES update(String resourceId, REQ dto);

    void delete(String resourceId);
}
