package org.animapolis.healthcare.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.animapolis.healthcare.exception.ResourceNotFoundException;
import org.animapolis.healthcare.mapper.MedicationAdministrationMapper;
import org.animapolis.healthcare.model.dto.request.MedicationAdministrationRequestDto;
import org.animapolis.healthcare.model.dto.response.MedicationAdministrationResponseDto;
import org.animapolis.healthcare.model.entity.MedicationAdministration;
import org.animapolis.healthcare.model.entity.Prescription;
import org.animapolis.healthcare.repository.MedicationAdministrationRepository;
import org.animapolis.healthcare.repository.MedicationRepository;
import org.animapolis.healthcare.repository.PrescriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultMedicationAdministrationService extends BaseEntityService implements MedicationAdministrationService {

    private final MedicationAdministrationMapper medicationAdministrationMapper;
    private final MedicationAdministrationRepository medicationAdministrationRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final MedicationRepository medicationRepository;

    @Transactional
    @Override
    public MedicationAdministrationResponseDto create(MedicationAdministrationRequestDto dto) {
        MedicationAdministration medicationAdministration = medicationAdministrationMapper.toEntity(dto);

        if (dto.getMedicationResourceId() != null) {
            Long medicationId = medicationRepository.findIdByResourceId(dto.getMedicationResourceId()).orElseThrow(
                    () -> createResourceNotFoundException("Medication", dto.getMedicationResourceId())
            );
            medicationAdministration.getMedication().setId(medicationId);
        }
        if (dto.getPrescriptionResourceId() != null) {
            Long prescriptionId = prescriptionRepository.findIdByResourceId(dto.getPrescriptionResourceId()).orElseThrow(
                    () -> createResourceNotFoundException("Prescription", dto.getPrescriptionResourceId())
            );
            medicationAdministration.getPrescription().setId(prescriptionId);
        }

        super.prepareForCreation(medicationAdministration);
        medicationAdministrationRepository.save(medicationAdministration);
        medicationAdministrationRepository.refresh(medicationAdministration);

        return medicationAdministrationMapper.toDto(medicationAdministration);
    }

    @Override
    public MedicationAdministrationResponseDto get(String resourceId) {
        MedicationAdministration medicationAdministration = medicationAdministrationRepository
                .findByResourceId(resourceId)
                .orElseThrow(
                        () -> createResourceNotFoundException(resourceId)
                );
        return medicationAdministrationMapper.toDto(medicationAdministration);
    }

    @Override
    public List<MedicationAdministrationResponseDto> getAll() {
        List<MedicationAdministration> medicationAdministrations = medicationAdministrationRepository.findAll();

        return medicationAdministrations.stream()
                .map(medicationAdministrationMapper::toDto)
                .toList();
    }

    @Transactional
    @Override
    public MedicationAdministrationResponseDto update(String resourceId, MedicationAdministrationRequestDto dto) {
        Long id = medicationAdministrationRepository.findIdByResourceId(resourceId).orElseThrow(
                () -> createResourceNotFoundException(resourceId)
        );

        MedicationAdministration medicationAdministration = medicationAdministrationMapper.toEntity(dto);

        if (dto.getMedicationResourceId() != null) {
            Long medicationId = medicationRepository.findIdByResourceId(dto.getMedicationResourceId()).orElseThrow(
                    () -> createResourceNotFoundException("Medication", dto.getMedicationResourceId())
            );
            medicationAdministration.getMedication().setId(medicationId);
        }
        if (dto.getPrescriptionResourceId() != null) {
            Long prescriptionId = prescriptionRepository.findIdByResourceId(dto.getPrescriptionResourceId()).orElseThrow(
                    () -> createResourceNotFoundException("Prescription", dto.getPrescriptionResourceId())
            );
            medicationAdministration.getPrescription().setId(prescriptionId);
        }

        super.prepareForUpdate(medicationAdministration);
        medicationAdministration.setId(id);

        medicationAdministration = medicationAdministrationRepository.saveAndFlush(medicationAdministration);
        medicationAdministrationRepository.refresh(medicationAdministration);
        return medicationAdministrationMapper.toDto(medicationAdministration);
    }

    @Override
    public void delete(String resourceId) {
        Long id = medicationAdministrationRepository.findIdByResourceId(resourceId).orElseThrow(
                () -> createResourceNotFoundException(resourceId)
        );
        medicationAdministrationRepository.deleteById(id);
    }

    private ResourceNotFoundException createResourceNotFoundException(String resourceId) {
        return createResourceNotFoundException("MedicationAdministration", resourceId);
    }

    private ResourceNotFoundException createResourceNotFoundException(String resourceType, String resourceId) {
        return new ResourceNotFoundException(resourceType + " with id = " + resourceId + " does not exist");
    }
}
