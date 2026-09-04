package com.animapolis.healthcare.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.animapolis.healthcare.exception.ValidationException;
import com.animapolis.healthcare.model.dto.request.PrescriptionRequestDto;
import com.animapolis.healthcare.model.dto.response.PrescriptionResponseDto;
import com.animapolis.healthcare.service.medication.PrescriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/prescription")
@RequiredArgsConstructor
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    @PostMapping
    public ResponseEntity<PrescriptionResponseDto> create(@RequestBody @Valid PrescriptionRequestDto prescriptionDto) {
        PrescriptionResponseDto createdPrescription = prescriptionService.create(prescriptionDto);

        return ResponseEntity
                .status(201)
                .body(createdPrescription);

    }

    @PutMapping("/{resourceId}")
    public ResponseEntity<PrescriptionResponseDto> update(@PathVariable("resourceId") UUID resourceId,
                                                          @RequestBody @Valid PrescriptionRequestDto prescriptionDto) {
        if (!Objects.equals(resourceId, prescriptionDto.getResourceId())) {
            throw new ValidationException("Path resource id should be equal to id of the resource provided in the request body");
        }

        PrescriptionResponseDto updatedPrescription = prescriptionService.update(resourceId, prescriptionDto);
        return ResponseEntity.ok(updatedPrescription);
    }

    @GetMapping("/{resourceId}")
    public ResponseEntity<PrescriptionResponseDto> get(@PathVariable("resourceId") UUID resourceId) {
        PrescriptionResponseDto prescription = prescriptionService.get(resourceId);

        return ResponseEntity.ok(prescription);
    }

    @GetMapping
    public ResponseEntity<List<PrescriptionResponseDto>> getAll() {
        List<PrescriptionResponseDto> prescriptions = prescriptionService.getAll();

        return ResponseEntity.ok(prescriptions);
    }

    @DeleteMapping("/{resourceId}")
    public ResponseEntity<Void> delete(@PathVariable("resourceId") UUID resourceId) {
        prescriptionService.delete(resourceId);

        return ResponseEntity
                .status(204)
                .build();
    }
}
