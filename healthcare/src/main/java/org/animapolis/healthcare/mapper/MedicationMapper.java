package org.animapolis.healthcare.mapper;

import org.animapolis.healthcare.model.dto.request.MedicationRequestDto;
import org.animapolis.healthcare.model.dto.response.MedicationResponseDto;
import org.animapolis.healthcare.model.entity.Medication;
import org.mapstruct.Mapper;

@Mapper
public interface MedicationMapper {

    MedicationResponseDto toDto(Medication medication);

    Medication toEntity(MedicationRequestDto medicationDto);
}
