package com.animapolis.healthcare.model.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class DtoResponseBase {

    private UUID resourceId;

    private LocalDateTime creationDate;

    private LocalDateTime lastUpdatedDate;
}
