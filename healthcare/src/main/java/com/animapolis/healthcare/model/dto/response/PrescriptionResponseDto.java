package com.animapolis.healthcare.model.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class PrescriptionResponseDto extends DtoResponseBase {

    private String name;

    private String instruction;

    private boolean isCompleted;

    private LocalDateTime authoredDate;

    private UUID authorResourceId;

    private UUID animalResourceId;
}
