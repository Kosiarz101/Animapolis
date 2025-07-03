package com.animapolis.employee.model.dto.request;

import com.animapolis.employee.model.type.ContractType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EmploymentContractRequestDto extends DtoRequestBase {

    private String resourceId;

    @NotNull(message = "Salary must be provided")
    private Integer salary;

    @NotNull(message = "Type must be provided")
    private ContractType type;

    @NotNull(message = "Start date must be provided")
    private LocalDate employmentStartDate;

    private LocalDate employmentEndDate;

    @NotNull(message = "Employment Contract must be connected to employee")
    private String employeeId;
}
