package com.animapolis.employee.model.dto.response;

import com.animapolis.employee.model.entity.EmploymentContract;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EmployeeResponseDto extends DtoResponseBase {

    private String resourceId;

    private String name;

    private String surname;

    private String pesel;

    private LocalDate birthdate;

    private String phoneNumber;

    private EmploymentContract employmentContract;
}
