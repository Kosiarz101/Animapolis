package org.animapolis.healthcare.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.animapolis.healthcare.exception.ResourceNotFoundException;
import org.animapolis.healthcare.mapper.PrescriptionMapper;
import org.animapolis.healthcare.model.dto.request.PrescriptionRequestDto;
import org.animapolis.healthcare.model.dto.response.PrescriptionResponseDto;
import org.animapolis.healthcare.model.entity.Prescription;
import org.animapolis.healthcare.repository.PrescriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultPrescriptionService extends BaseEntityService implements PrescriptionService  {

    private final PrescriptionMapper prescriptionMapper;
    private final PrescriptionRepository prescriptionRepository;

    @Transactional
    @Override
    public PrescriptionResponseDto create(PrescriptionRequestDto prescriptionRequestDto) {
        Prescription prescription = prescriptionMapper.toEntity(prescriptionRequestDto);

        super.prepareForCreation(prescription);
        prescriptionRepository.save(prescription);
        prescriptionRepository.refresh(prescription);
        prescription = prescriptionRepository.findById(prescription.getId()).get();

        return prescriptionMapper.toDto(prescription);
    }

    @Override
    public PrescriptionResponseDto get(String resourceId) {
        Prescription prescription = prescriptionRepository.findByResourceId(resourceId).orElseThrow(
                () -> createResourceNotFoundException(resourceId)
        );
        return prescriptionMapper.toDto(prescription);
    }

    @Override
    public List<PrescriptionResponseDto> getAll() {
        List<Prescription> prescriptions = prescriptionRepository.findAll();

        return prescriptions.stream()
                .map(prescriptionMapper::toDto)
                .toList();
    }

    @Transactional
    @Override
    public PrescriptionResponseDto update(String resourceId, PrescriptionRequestDto dto) {
        Long id = prescriptionRepository.findIdByResourceId(resourceId).orElseThrow(
                () -> createResourceNotFoundException(resourceId)
        );

        Prescription prescription = prescriptionMapper.toEntity(dto);
        super.prepareForUpdate(prescription);
        prescription.setId(id);

        prescription = prescriptionRepository.saveAndFlush(prescription);
        prescriptionRepository.refresh(prescription);
        return prescriptionMapper.toDto(prescription);
    }

    @Transactional
    @Override
    public void delete(String resourceId) {
        Long id = prescriptionRepository.findIdByResourceId(resourceId).orElseThrow(
                () -> createResourceNotFoundException(resourceId)
        );
        prescriptionRepository.deleteById(id);
    }

    private ResourceNotFoundException createResourceNotFoundException(String resourceId) {
        return new ResourceNotFoundException("Prescription with id = " + resourceId + " does not exist");
    }
}
