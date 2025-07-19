package org.animapolis.healthcare.mapper;

import org.animapolis.healthcare.model.dto.request.MedicationAdministrationRequestDto;
import org.animapolis.healthcare.model.dto.request.MedicationRequestDto;
import org.animapolis.healthcare.model.dto.response.MedicationAdministrationResponseDto;
import org.animapolis.healthcare.model.entity.Medication;
import org.animapolis.healthcare.model.entity.MedicationAdministration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface MedicationAdministrationMapper {

    @Mapping(source = "medication.resourceId", target = "medicationResourceId")
    @Mapping(source = "prescription.resourceId", target = "prescriptionResourceId")
    MedicationAdministrationResponseDto toDto(MedicationAdministration medicationAdministration);

    @Mapping(source = "medicationResourceId", target = "medication.resourceId")
    @Mapping(source = "prescriptionResourceId", target = "prescription.resourceId")
    MedicationAdministration toEntity(MedicationAdministrationRequestDto medicationAdministrationDto);
}
