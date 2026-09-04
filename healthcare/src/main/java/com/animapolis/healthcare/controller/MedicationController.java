package com.animapolis.healthcare.controller;

import com.animapolis.healthcare.exception.ValidationException;
import com.animapolis.healthcare.model.dto.request.MedicationRequestDto;
import com.animapolis.healthcare.model.dto.response.MedicationResponseDto;
import com.animapolis.healthcare.service.medication.MedicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/medication")
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
    public ResponseEntity<MedicationResponseDto> update(@PathVariable("resourceId") UUID resourceId,
                                                          @RequestBody @Valid MedicationRequestDto medicationDto) {
        if (!Objects.equals(resourceId, medicationDto.getResourceId())) {
            throw new ValidationException("Path resource id should be equal to id of the resource provided in the request body");
        }

        MedicationResponseDto updatedMedication = medicationService.update(resourceId, medicationDto);
        return ResponseEntity.ok(updatedMedication);
    }

    @GetMapping("/{resourceId}")
    public ResponseEntity<MedicationResponseDto> get(@PathVariable("resourceId") UUID resourceId) {
        MedicationResponseDto medication = medicationService.get(resourceId);

        return ResponseEntity.ok(medication);
    }

    @GetMapping
    public ResponseEntity<List<MedicationResponseDto>> getAll() {
        List<MedicationResponseDto> medications = medicationService.getAll();

        return ResponseEntity.ok(medications);
    }

    @DeleteMapping("/{resourceId}")
    public ResponseEntity<Void> delete(@PathVariable("resourceId") UUID resourceId) {
        medicationService.delete(resourceId);

        return ResponseEntity
                .status(204)
                .build();
    }
}
