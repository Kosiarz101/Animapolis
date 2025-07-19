package org.animapolis.healthcare.model.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicationPrescriptionResponseDto extends DtoResponseBase {

    private Double dosageValue;

    private String dosageUnit;

    private String dosageInstruction;

    private String medicationResourceId;

    private String prescriptionResourceId;
}
