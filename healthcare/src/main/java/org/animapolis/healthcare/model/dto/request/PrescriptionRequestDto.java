package org.animapolis.healthcare.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PrescriptionRequestDto extends DtoRequestBase {

    private String resourceId;

    @NotBlank(message = "Name must be provided")
    private String name;

    private String instruction;

    @NotNull(message = "Prescription must contain information whether it was completed")
    private boolean isCompleted;

    private LocalDateTime authoredDate;

    private String animalResourceId;
}
