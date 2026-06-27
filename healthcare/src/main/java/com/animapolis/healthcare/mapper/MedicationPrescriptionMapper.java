package com.animapolis.healthcare.mapper;

import com.animapolis.healthcare.model.dto.request.MedicationPrescriptionRequestDto;
import com.animapolis.healthcare.model.dto.response.MedicationPrescriptionResponseDto;
import com.animapolis.healthcare.model.entity.MedicationPrescription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface MedicationPrescriptionMapper {

    @Mapping(source = "medication.resourceId", target = "medicationResourceId")
    @Mapping(source = "prescription.resourceId", target = "prescriptionResourceId")
    MedicationPrescriptionResponseDto toDto(MedicationPrescription medicationPrescription);

    @Mapping(source = "medicationResourceId", target = "medication.resourceId")
    @Mapping(source = "prescriptionResourceId", target = "prescription.resourceId")
    MedicationPrescription toEntity(MedicationPrescriptionRequestDto medicationPrescriptionDto);
}
