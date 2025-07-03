package com.animapolis.employee.controller;

import com.animapolis.employee.exception.ValidationException;
import com.animapolis.employee.model.dto.request.EmployeeRequestDto;
import com.animapolis.employee.model.dto.response.EmployeeResponseDto;
import com.animapolis.employee.service.EmployeeService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Tag(
        name = "Employee API",
        description = "REST API for Employees in Animapolis"
)
@RestController
@RequestMapping(path = "/Employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
    })
    @PostMapping
    public ResponseEntity<EmployeeResponseDto> create(@Valid @RequestBody EmployeeRequestDto employeeDto) {
        EmployeeResponseDto createdEmployee = employeeService.create(employeeDto);

        return ResponseEntity
                .status(201)
                .body(createdEmployee);
    }

    @GetMapping("/{resourceId}")
    public ResponseEntity<EmployeeResponseDto> get(@PathVariable("resourceId") String resourceId) {
        EmployeeResponseDto employee = employeeService.get(resourceId);

        return ResponseEntity
                .status(200)
                .body(employee);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDto>> getAll() {
        List<EmployeeResponseDto> employees = employeeService.getAll();
        return ResponseEntity.ok(employees);
    }

    @PutMapping("/{resourceId}")
    public ResponseEntity<EmployeeResponseDto> update(@PathVariable("resourceId") String resourceId, @Valid @RequestBody EmployeeRequestDto employeeDto) {
        if (!Objects.equals(resourceId, employeeDto.getResourceId())) {
            throw new ValidationException("Path resource id should be equal to id of the resource provided in the request body");
        }
        EmployeeResponseDto updatedEmployee = employeeService.update(resourceId, employeeDto);

        return ResponseEntity.ok(updatedEmployee);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted"),
    })
    @DeleteMapping("/{resourceId}")
    public ResponseEntity<Void> delete(@PathVariable("resourceId") String resourceId) {
        employeeService.delete(resourceId);
        return ResponseEntity
                .status(204)
                .build();
    }
}
