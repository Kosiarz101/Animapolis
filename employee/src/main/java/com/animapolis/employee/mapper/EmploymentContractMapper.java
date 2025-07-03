package com.animapolis.employee.mapper;

import com.animapolis.employee.model.dto.request.EmploymentContractRequestDto;
import com.animapolis.employee.model.dto.response.EmploymentContractResponseDto;
import com.animapolis.employee.model.entity.EmploymentContract;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmploymentContractMapper {

    @Mapping(source = "employee.resourceId", target = "employeeId")
    EmploymentContractResponseDto toDto(EmploymentContract employmentContract);

    @Mapping(source = "employeeId", target = "employee.resourceId")
    EmploymentContract toEntity(EmploymentContractRequestDto employmentContractDto);
}
