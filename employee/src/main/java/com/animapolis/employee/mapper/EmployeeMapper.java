package com.animapolis.employee.mapper;

import com.animapolis.employee.model.dto.request.EmployeeRequestDto;
import com.animapolis.employee.model.dto.response.EmployeeResponseDto;
import com.animapolis.employee.model.entity.Employee;
import org.mapstruct.Mapper;

@Mapper
public interface EmployeeMapper {

    EmployeeResponseDto toDto(Employee employee);

    Employee toEntity(EmployeeRequestDto employeeDto);
}
