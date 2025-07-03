package com.animapolis.employee.model.dto.response;

import com.animapolis.employee.model.type.ContractType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EmploymentContractResponseDto extends DtoResponseBase {

    private String resourceId;

    private Integer salary;

    private ContractType type;

    private LocalDate employmentStartDate;

    private LocalDate employmentEndDate;

    private String employeeId;
}
