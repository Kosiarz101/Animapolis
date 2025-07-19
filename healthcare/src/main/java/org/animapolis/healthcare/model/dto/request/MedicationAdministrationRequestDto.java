package org.animapolis.healthcare.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MedicationAdministrationRequestDto extends DtoRequestBase {

    private String resourceId;

    private LocalDateTime administrationDate;

    @NotNull(message = "Dosage value must be provided")
    private Double dosageValue;

    @NotBlank(message = "Dosage unit must be provided")
    private String dosageUnit;

    @NotBlank(message = "Medication administration must be connected to medication")
    private String medicationResourceId;

    @NotBlank(message = "Medication administration must be connected to prescription")
    private String prescriptionResourceId;
}
