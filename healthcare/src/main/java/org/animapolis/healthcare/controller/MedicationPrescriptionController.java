package org.animapolis.healthcare.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.animapolis.healthcare.exception.ValidationException;
import org.animapolis.healthcare.model.dto.request.MedicationPrescriptionRequestDto;
import org.animapolis.healthcare.model.dto.response.MedicationPrescriptionResponseDto;
import org.animapolis.healthcare.service.MedicationPrescriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/MedicationPrescription")
@RequiredArgsConstructor
public class MedicationPrescriptionController {

    private final MedicationPrescriptionService medicationPrescriptionService;

    @PostMapping
    public ResponseEntity<MedicationPrescriptionResponseDto> create(@RequestBody @Valid MedicationPrescriptionRequestDto dto) {
        MedicationPrescriptionResponseDto createdMedicationPrescription = medicationPrescriptionService.create(dto);

        return ResponseEntity
                .status(201)
                .body(createdMedicationPrescription);

    }

    @PutMapping("/{resourceId}")
    public ResponseEntity<MedicationPrescriptionResponseDto> update(@PathVariable("resourceId") String resourceId,
                                                          @RequestBody @Valid MedicationPrescriptionRequestDto dto) {
        if (!Objects.equals(resourceId, dto.getResourceId())) {
            throw new ValidationException("Path resource id should be equal to id of the resource provided in the request body");
        }

        MedicationPrescriptionResponseDto updatedMedicationPrescription = medicationPrescriptionService.update(resourceId, dto);
        return ResponseEntity.ok(updatedMedicationPrescription);
    }

    @GetMapping("/{resourceId}")
    public ResponseEntity<MedicationPrescriptionResponseDto> get(@PathVariable("resourceId") String resourceId) {
        MedicationPrescriptionResponseDto medication = medicationPrescriptionService.get(resourceId);

        return ResponseEntity.ok(medication);
    }

    @GetMapping
    public ResponseEntity<List<MedicationPrescriptionResponseDto>> getAll() {
        List<MedicationPrescriptionResponseDto> medications = medicationPrescriptionService.getAll();

        return ResponseEntity.ok(medications);
    }

    @DeleteMapping("/{resourceId}")
    public ResponseEntity<Void> delete(@PathVariable("resourceId") String resourceId) {
        medicationPrescriptionService.delete(resourceId);

        return ResponseEntity
                .status(204)
                .build();
    }
}
