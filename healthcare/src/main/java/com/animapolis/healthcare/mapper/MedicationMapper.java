package com.animapolis.healthcare.mapper;

import com.animapolis.healthcare.model.dto.request.MedicationRequestDto;
import com.animapolis.healthcare.model.dto.response.MedicationResponseDto;
import com.animapolis.healthcare.model.entity.Medication;
import org.mapstruct.Mapper;

@Mapper
public interface MedicationMapper {

    MedicationResponseDto toDto(Medication medication);

    Medication toEntity(MedicationRequestDto medicationDto);
}
