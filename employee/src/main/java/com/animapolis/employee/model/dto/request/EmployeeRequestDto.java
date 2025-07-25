package com.animapolis.employee.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EmployeeRequestDto extends DtoRequestBase {

    private String resourceId;

    @NotBlank(message = "Name must be provided")
    private String name;

    @NotBlank(message = "Surname must be provided")
    private String surname;

    @Pattern(regexp = "\\d{11}", message = "PESEL must contain 11 digits")
    private String pesel;

    @NotNull(message = "Birthdate must be provided")
    @Past(message = "Birthdate must be in the past")
    private LocalDate birthdate;

    @Pattern(regexp = "^\\d{1,15}$", message = "Phone number cannot be longer than 15 digits and must contain only digits")
    private String phoneNumber;
}
