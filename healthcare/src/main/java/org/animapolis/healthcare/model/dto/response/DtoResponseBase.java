package org.animapolis.healthcare.model.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DtoResponseBase {

    private String resourceId;

    private LocalDateTime creationDate;

    private LocalDateTime lastUpdatedDate;
}
