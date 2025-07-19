package org.animapolis.healthcare.model.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MedicationAdministrationResponseDto extends DtoResponseBase {

    private LocalDateTime administrationDate;

    private Double dosageValue;

    private String dosageUnit;

    private String medicationResourceId;

    private String prescriptionResourceId;
}
