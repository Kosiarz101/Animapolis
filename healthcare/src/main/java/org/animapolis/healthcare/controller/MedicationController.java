package org.animapolis.healthcare.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.animapolis.healthcare.exception.ValidationException;
import org.animapolis.healthcare.model.dto.request.MedicationRequestDto;
import org.animapolis.healthcare.model.dto.response.MedicationResponseDto;
import org.animapolis.healthcare.service.MedicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/Medication")
@RequiredArgsConstructor
public class MedicationController {

    private final MedicationService medicationService;

    @PostMapping
    public ResponseEntity<MedicationResponseDto> create(@RequestBody @Valid MedicationRequestDto medicationDto) {
        MedicationResponseDto createdMedication = medicationService.create(medicationDto);

        return ResponseEntity
                .status(201)
                .body(createdMedication);

    }

    @PutMapping("/{resourceId}")
    public ResponseEntity<MedicationResponseDto> update(@PathVariable("resourceId") String resourceId,
                                                          @RequestBody @Valid MedicationRequestDto medicationDto) {
        if (!Objects.equals(resourceId, medicationDto.getResourceId())) {
            throw new ValidationException("Path resource id should be equal to id of the resource provided in the request body");
        }

        MedicationResponseDto updatedMedication = medicationService.update(resourceId, medicationDto);
        return ResponseEntity.ok(updatedMedication);
    }

    @GetMapping("/{resourceId}")
    public ResponseEntity<MedicationResponseDto> get(@PathVariable("resourceId") String resourceId) {
        MedicationResponseDto medication = medicationService.get(resourceId);

        return ResponseEntity.ok(medication);
    }

    @GetMapping
    public ResponseEntity<List<MedicationResponseDto>> getAll() {
        List<MedicationResponseDto> medications = medicationService.getAll();

        return ResponseEntity.ok(medications);
    }

    @DeleteMapping("/{resourceId}")
    public ResponseEntity<Void> delete(@PathVariable("resourceId") String resourceId) {
        medicationService.delete(resourceId);

        return ResponseEntity
                .status(204)
                .build();
    }
}
