package org.animapolis.healthcare.mapper;

import org.animapolis.healthcare.model.dto.request.PrescriptionRequestDto;
import org.animapolis.healthcare.model.dto.response.PrescriptionResponseDto;
import org.animapolis.healthcare.model.entity.Prescription;
import org.mapstruct.Mapper;

@Mapper
public interface PrescriptionMapper {

    PrescriptionResponseDto toDto(Prescription prescription);

    Prescription toEntity(PrescriptionRequestDto prescriptionDto);
}
