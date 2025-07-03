package com.animapolis.employee.controller;

import com.animapolis.employee.exception.ValidationException;
import com.animapolis.employee.model.dto.request.EmploymentContractRequestDto;
import com.animapolis.employee.model.dto.response.EmploymentContractResponseDto;
import com.animapolis.employee.service.EmploymentContractService;
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
        name = "Employment Contract API",
        description = "REST API for employment contracts in Animapolis"
)
@RestController
@RequestMapping("EmploymentContract")
@RequiredArgsConstructor
public class EmploymentContractController {

    private final EmploymentContractService employmentContractService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<EmploymentContractResponseDto> create(@Valid @RequestBody EmploymentContractRequestDto employmentContractDto) {
        EmploymentContractResponseDto createdEmploymentContract = employmentContractService.create(employmentContractDto);
        return ResponseEntity
                .status(201)
                .body(createdEmploymentContract);
    }

    @GetMapping("/{resourceId}")
    public ResponseEntity<EmploymentContractResponseDto> get(@PathVariable("resourceId") String resourceId) {
        EmploymentContractResponseDto createdEmploymentContract = employmentContractService.get(resourceId);

        return ResponseEntity
                .status(200)
                .body(createdEmploymentContract);
    }

    @GetMapping
    public ResponseEntity<List<EmploymentContractResponseDto>> getAll() {
        List<EmploymentContractResponseDto> employmentContracts = employmentContractService.getAll();
        return ResponseEntity.ok(employmentContracts);
    }

    @PutMapping("/{resourceId}")
    public ResponseEntity<EmploymentContractResponseDto> update(@PathVariable("resourceId") String resourceId,
                                                     @Valid @RequestBody EmploymentContractRequestDto employmentContractDto) {
        if (!Objects.equals(resourceId, employmentContractDto.getResourceId())) {
            throw new ValidationException("Path resource id should be equal to id of the resource provided in the request body");
        }
        EmploymentContractResponseDto updatedEmploymentContract = employmentContractService.update(resourceId, employmentContractDto);

        return ResponseEntity.ok(updatedEmploymentContract);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted")
    })
    @DeleteMapping("/{resourceId}")
    public ResponseEntity<Void> delete(@PathVariable("resourceId") String resourceId) {
        employmentContractService.delete(resourceId);
        return ResponseEntity
                .status(204)
                .build();
    }
}
