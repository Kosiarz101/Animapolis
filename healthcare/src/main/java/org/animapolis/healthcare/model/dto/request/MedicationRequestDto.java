package org.animapolis.healthcare.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MedicationRequestDto extends DtoRequestBase {

    private String resourceId;

    @NotBlank(message = "Name must be provided")
    private String name;

    @NotBlank(message = "Form must be provided")
    private String form;

    private String description;

    @NotNull(message = "Expiration date must be provided")
    private LocalDateTime expirationDate;
}
