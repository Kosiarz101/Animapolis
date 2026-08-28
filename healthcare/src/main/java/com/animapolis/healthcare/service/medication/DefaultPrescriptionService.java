package com.animapolis.healthcare.service.medication;

import com.animapolis.healthcare.exception.ResourceNotFoundException;
import com.animapolis.healthcare.mapper.PrescriptionMapper;
import com.animapolis.healthcare.model.dto.request.PrescriptionRequestDto;
import com.animapolis.healthcare.model.dto.response.PrescriptionResponseDto;
import com.animapolis.healthcare.model.entity.Prescription;
import com.animapolis.healthcare.repository.PrescriptionRepository;
import com.animapolis.healthcare.service.employee.EmployeeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultPrescriptionService extends BaseEntityService implements PrescriptionService  {

    private final PrescriptionMapper prescriptionMapper;
    private final PrescriptionRepository prescriptionRepository;
    private final EmployeeService employeeService;

    @Transactional
    @Override
    public PrescriptionResponseDto create(PrescriptionRequestDto prescriptionRequestDto) {
        Prescription prescription = prescriptionMapper.toEntity(prescriptionRequestDto);

        super.prepareForCreation(prescription);

        if (!employeeService.exists(prescriptionRequestDto.getAuthorResourceId())) {
            throw new ResourceNotFoundException(
                    "Author with id = " + prescriptionRequestDto.getAuthorResourceId() + " does not exist");
        }

        prescriptionRepository.save(prescription);
        prescriptionRepository.refresh(prescription);

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
