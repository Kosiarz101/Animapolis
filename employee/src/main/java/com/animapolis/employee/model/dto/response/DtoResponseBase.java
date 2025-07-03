package com.animapolis.employee.model.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DtoResponseBase {

    private LocalDateTime creationDate;

    private LocalDateTime lastUpdatedDate;
}
