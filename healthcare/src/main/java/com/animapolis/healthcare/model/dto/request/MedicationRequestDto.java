package com.animapolis.healthcare.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class MedicationRequestDto extends DtoRequestBase {

    private UUID resourceId;

    @NotBlank(message = "Name must be provided")
    private String name;

    @NotBlank(message = "Form must be provided")
    private String form;

    private String description;

    @NotNull(message = "Expiration date must be provided")
    private LocalDateTime expirationDate;
}
