package org.animapolis.healthcare.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicationPrescriptionRequestDto extends DtoRequestBase {

    private String resourceId;

    @NotNull(message = "Dosage value must be provided")
    private Double dosageValue;

    @NotBlank(message = "Dosage unit must be provided")
    private String dosageUnit;

    private String dosageInstruction;

    @NotBlank(message = "Medication prescription must be connected to medication")
    private String medicationResourceId;

    @NotBlank(message = "Medication prescription must be connected to prescription")
    private String prescriptionResourceId;
}
