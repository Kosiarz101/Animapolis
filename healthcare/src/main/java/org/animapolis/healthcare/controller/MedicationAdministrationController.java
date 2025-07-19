package org.animapolis.healthcare.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.animapolis.healthcare.exception.ValidationException;
import org.animapolis.healthcare.model.dto.request.MedicationAdministrationRequestDto;
import org.animapolis.healthcare.model.dto.response.MedicationAdministrationResponseDto;
import org.animapolis.healthcare.service.MedicationAdministrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/MedicationAdministration")
@RequiredArgsConstructor
public class MedicationAdministrationController {

    private final MedicationAdministrationService medicationAdministrationService;

    @PostMapping
    public ResponseEntity<MedicationAdministrationResponseDto> create(@RequestBody @Valid MedicationAdministrationRequestDto dto) {
        MedicationAdministrationResponseDto createdMedicationAdministration = medicationAdministrationService.create(dto);

        return ResponseEntity
                .status(201)
                .body(createdMedicationAdministration);

    }

    @PutMapping("/{resourceId}")
    public ResponseEntity<MedicationAdministrationResponseDto> update(@PathVariable("resourceId") String resourceId,
                                                          @RequestBody @Valid MedicationAdministrationRequestDto dto) {
        if (!Objects.equals(resourceId, dto.getResourceId())) {
            throw new ValidationException("Path resource id should be equal to id of the resource provided in the request body");
        }

        MedicationAdministrationResponseDto updatedMedicationAdministration = medicationAdministrationService.update(resourceId, dto);
        return ResponseEntity.ok(updatedMedicationAdministration);
    }

    @GetMapping("/{resourceId}")
    public ResponseEntity<MedicationAdministrationResponseDto> get(@PathVariable("resourceId") String resourceId) {
        MedicationAdministrationResponseDto medication = medicationAdministrationService.get(resourceId);

        return ResponseEntity.ok(medication);
    }

    @GetMapping
    public ResponseEntity<List<MedicationAdministrationResponseDto>> getAll() {
        List<MedicationAdministrationResponseDto> medications = medicationAdministrationService.getAll();

        return ResponseEntity.ok(medications);
    }

    @DeleteMapping("/{resourceId}")
    public ResponseEntity<Void> delete(@PathVariable("resourceId") String resourceId) {
        medicationAdministrationService.delete(resourceId);

        return ResponseEntity
                .status(204)
                .build();
    }
}
