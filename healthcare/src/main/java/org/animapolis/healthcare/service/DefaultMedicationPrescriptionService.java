package org.animapolis.healthcare.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.animapolis.healthcare.exception.ResourceNotFoundException;
import org.animapolis.healthcare.mapper.MedicationPrescriptionMapper;
import org.animapolis.healthcare.model.dto.request.MedicationPrescriptionRequestDto;
import org.animapolis.healthcare.model.dto.response.MedicationPrescriptionResponseDto;
import org.animapolis.healthcare.model.entity.MedicationPrescription;
import org.animapolis.healthcare.repository.MedicationPrescriptionRepository;
import org.animapolis.healthcare.repository.MedicationRepository;
import org.animapolis.healthcare.repository.PrescriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultMedicationPrescriptionService extends BaseEntityService implements MedicationPrescriptionService {

    private final MedicationPrescriptionMapper medicationPrescriptionMapper;
    private final MedicationPrescriptionRepository medicationPrescriptionRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final MedicationRepository medicationRepository;

    @Transactional
    @Override
    public MedicationPrescriptionResponseDto create(MedicationPrescriptionRequestDto dto) {
        MedicationPrescription medicationPrescription = medicationPrescriptionMapper.toEntity(dto);

        if (dto.getMedicationResourceId() != null) {
            Long medicationId = medicationRepository.findIdByResourceId(dto.getMedicationResourceId()).orElseThrow(
                    () -> createResourceNotFoundException("Medication", dto.getMedicationResourceId())
            );
            medicationPrescription.getMedication().setId(medicationId);
        }
        if (dto.getPrescriptionResourceId() != null) {
            Long prescriptionId = prescriptionRepository.findIdByResourceId(dto.getPrescriptionResourceId()).orElseThrow(
                    () -> createResourceNotFoundException("Prescription", dto.getPrescriptionResourceId())
            );
            medicationPrescription.getPrescription().setId(prescriptionId);
        }

        super.prepareForCreation(medicationPrescription);
        medicationPrescriptionRepository.save(medicationPrescription);
        medicationPrescriptionRepository.refresh(medicationPrescription);

        return medicationPrescriptionMapper.toDto(medicationPrescription);
    }

    @Override
    public MedicationPrescriptionResponseDto get(String resourceId) {
        MedicationPrescription medicationPrescription = medicationPrescriptionRepository
                .findByResourceId(resourceId)
                .orElseThrow(
                        () -> createResourceNotFoundException(resourceId)
                );
        return medicationPrescriptionMapper.toDto(medicationPrescription);
    }

    @Override
    public List<MedicationPrescriptionResponseDto> getAll() {
        List<MedicationPrescription> medicationPrescriptions = medicationPrescriptionRepository.findAll();

        return medicationPrescriptions.stream()
                .map(medicationPrescriptionMapper::toDto)
                .toList();
    }

    @Transactional
    @Override
    public MedicationPrescriptionResponseDto update(String resourceId, MedicationPrescriptionRequestDto dto) {
        Long id = medicationPrescriptionRepository.findIdByResourceId(resourceId).orElseThrow(
                () -> createResourceNotFoundException(resourceId)
        );

        MedicationPrescription medicationPrescription = medicationPrescriptionMapper.toEntity(dto);

        if (dto.getMedicationResourceId() != null) {
            Long medicationId = medicationRepository.findIdByResourceId(dto.getMedicationResourceId()).orElseThrow(
                    () -> createResourceNotFoundException("Medication", dto.getMedicationResourceId())
            );
            medicationPrescription.getMedication().setId(medicationId);
        }
        if (dto.getPrescriptionResourceId() != null) {
            Long prescriptionId = prescriptionRepository.findIdByResourceId(dto.getPrescriptionResourceId()).orElseThrow(
                    () -> createResourceNotFoundException("Prescription", dto.getPrescriptionResourceId())
            );
            medicationPrescription.getPrescription().setId(prescriptionId);
        }

        super.prepareForUpdate(medicationPrescription);
        medicationPrescription.setId(id);

        medicationPrescription = medicationPrescriptionRepository.saveAndFlush(medicationPrescription);
        medicationPrescriptionRepository.refresh(medicationPrescription);
        return medicationPrescriptionMapper.toDto(medicationPrescription);
    }

    @Override
    public void delete(String resourceId) {
        Long id = medicationPrescriptionRepository.findIdByResourceId(resourceId).orElseThrow(
                () -> createResourceNotFoundException(resourceId)
        );
        medicationPrescriptionRepository.deleteById(id);
    }

    private ResourceNotFoundException createResourceNotFoundException(String resourceId) {
        return createResourceNotFoundException("MedicationPrescription", resourceId);
    }

    private ResourceNotFoundException createResourceNotFoundException(String resourceType, String resourceId) {
        return new ResourceNotFoundException(resourceType + " with id = " + resourceId + " does not exist");
    }
}
