package org.animapolis.healthcare.model.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MedicationResponseDto extends DtoResponseBase {

    private String name;

    private String form;

    private String description;

    private LocalDateTime expirationDate;
}
