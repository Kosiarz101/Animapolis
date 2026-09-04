package com.animapolis.healthcare.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class MedicationPrescriptionRequestDto extends DtoRequestBase {

    private UUID resourceId;

    @NotNull(message = "Medication prescription must be connected to medication")
    private UUID medicationResourceId;

    @NotNull(message = "Medication prescription must be connected to prescription")
    private UUID prescriptionResourceId;

    @NotNull(message = "Dosage value must be provided")
    private Double dosageValue;

    @NotBlank(message = "Dosage unit must be provided")
    private String dosageUnit;

    private String dosageInstruction;
}
