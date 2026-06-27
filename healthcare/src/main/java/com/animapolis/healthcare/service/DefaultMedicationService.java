package com.animapolis.healthcare.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import com.animapolis.healthcare.exception.ResourceNotFoundException;
import com.animapolis.healthcare.mapper.MedicationMapper;
import com.animapolis.healthcare.model.dto.request.MedicationRequestDto;
import com.animapolis.healthcare.model.dto.response.MedicationResponseDto;
import com.animapolis.healthcare.model.entity.Medication;
import com.animapolis.healthcare.repository.MedicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultMedicationService extends BaseEntityService implements MedicationService  {

    private final MedicationMapper medicationMapper;
    private final MedicationRepository medicationRepository;

    @Transactional
    @Override
    public MedicationResponseDto create(MedicationRequestDto medicationRequestDto) {
        Medication medication = medicationMapper.toEntity(medicationRequestDto);

        super.prepareForCreation(medication);
        medicationRepository.save(medication);
        medicationRepository.refresh(medication);

        return medicationMapper.toDto(medication);
    }

    @Override
    public MedicationResponseDto get(String resourceId) {
        Medication medication = medicationRepository.findByResourceId(resourceId).orElseThrow(
                () -> createResourceNotFoundException(resourceId)
        );
        return medicationMapper.toDto(medication);
    }

    @Override
    public List<MedicationResponseDto> getAll() {
        List<Medication> medications = medicationRepository.findAll();

        return medications.stream()
                .map(medicationMapper::toDto)
                .toList();
    }

    @Transactional
    @Override
    public MedicationResponseDto update(String resourceId, MedicationRequestDto dto) {
        Long id = medicationRepository.findIdByResourceId(resourceId).orElseThrow(
                () -> createResourceNotFoundException(resourceId)
        );

        Medication medication = medicationMapper.toEntity(dto);
        super.prepareForUpdate(medication);
        medication.setId(id);

        medication = medicationRepository.saveAndFlush(medication);
        medicationRepository.refresh(medication);
        return medicationMapper.toDto(medication);
    }

    @Transactional
    @Override
    public void delete(String resourceId) {
        Long id = medicationRepository.findIdByResourceId(resourceId).orElseThrow(
                () -> createResourceNotFoundException(resourceId)
        );
        medicationRepository.deleteById(id);
    }

    private ResourceNotFoundException createResourceNotFoundException(String resourceId) {
        return new ResourceNotFoundException("Medication with id = " + resourceId + " does not exist");
    }
}
