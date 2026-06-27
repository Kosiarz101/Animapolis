package com.animapolis.healthcare.mapper;

import com.animapolis.healthcare.model.dto.request.PrescriptionRequestDto;
import com.animapolis.healthcare.model.dto.response.PrescriptionResponseDto;
import com.animapolis.healthcare.model.entity.Prescription;
import org.mapstruct.Mapper;

@Mapper
public interface PrescriptionMapper {

    PrescriptionResponseDto toDto(Prescription prescription);

    Prescription toEntity(PrescriptionRequestDto prescriptionDto);
}
