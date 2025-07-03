package com.animapolis.employee.service;

import com.animapolis.employee.exception.ResourceNotFoundException;
import com.animapolis.employee.exception.ValidationException;
import com.animapolis.employee.mapper.EmploymentContractMapper;
import com.animapolis.employee.model.dto.request.EmploymentContractRequestDto;
import com.animapolis.employee.model.dto.response.EmploymentContractResponseDto;
import com.animapolis.employee.model.entity.Employee;
import com.animapolis.employee.model.entity.EmploymentContract;
import com.animapolis.employee.repository.EmployeeRepository;
import com.animapolis.employee.repository.EmploymentContractRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultEmploymentContractService extends BaseEntityService implements EmploymentContractService {

    private final EmploymentContractRepository employmentContractRepository;
    private final EmployeeRepository employeeRepository;
    private final EmploymentContractMapper employmentContractMapper;

    @Transactional
    @Override
    public EmploymentContractResponseDto create(EmploymentContractRequestDto dto) {
        EmploymentContract employmentContract = employmentContractMapper.toEntity(dto);
        super.prepareForCreation(employmentContract);

        Employee employee = employmentContract.getEmployee();
        if (employee != null && employee.getResourceId() != null) {
            Long employeeId = employeeRepository.findIdByResourceId(employee.getResourceId()).orElseThrow(
                    () -> new ResourceNotFoundException("Employee with id = " + employee.getResourceId() + " does not exist")
            );
            employee.setId(employeeId);
            if (employmentContractRepository.existsByEmployeeId(employee.getId())) {
                throw new ValidationException("This employee has contract already");
            }
        }

        employmentContractRepository.save(employmentContract);
        employmentContractRepository.refresh(employmentContract);
        employmentContract = employmentContractRepository.findById(employmentContract.getId()).get();
        return employmentContractMapper.toDto(employmentContract);
    }

    @Override
    public EmploymentContractResponseDto get(String resourceId) {
        EmploymentContract employmentContract = employmentContractRepository.findByResourceId(resourceId).orElseThrow(
                () -> new ResourceNotFoundException("EmploymentContract with id = " + resourceId + " does not exist")
        );
        return employmentContractMapper.toDto(employmentContract);
    }

    @Override
    public List<EmploymentContractResponseDto> getAll() {
        List<EmploymentContract> employmentContracts = employmentContractRepository.findAll();

        return employmentContracts.stream()
                .map(employmentContractMapper::toDto)
                .toList();
    }

    @Transactional
    @Override
    public EmploymentContractResponseDto update(String resourceId, EmploymentContractRequestDto dto) {
        Long id = employmentContractRepository.findIdByResourceId(resourceId).orElseThrow(
                () -> new ResourceNotFoundException("EmploymentContract with id = " + resourceId + " does not exist")
        );

        EmploymentContract employmentContract = employmentContractMapper.toEntity(dto);
        employmentContract.setId(id);
        super.prepareForUpdate(employmentContract);

        Employee employee = employmentContract.getEmployee();
        if (employee != null && employee.getResourceId() != null) {
            Long employeeId = employeeRepository.findIdByResourceId(employee.getResourceId()).orElseThrow(
                    () -> new ResourceNotFoundException("Employee with id = " + employee.getResourceId() + " does not exist")
            );
            employee.setId(employeeId);
            Optional<Long> employmentContractOfRequestEmployee = employmentContractRepository.findByEmployeeId(employee.getId());
            if (employmentContractOfRequestEmployee.isPresent() && !employmentContractOfRequestEmployee.get().equals(id)) {
                throw new ValidationException("This employee has contract already");
            }
        }

        employmentContract = employmentContractRepository.saveAndFlush(employmentContract);
        employmentContractRepository.refresh(employmentContract);
        employmentContract = employmentContractRepository.findById(employmentContract.getId()).get();
        return employmentContractMapper.toDto(employmentContract);
    }

    @Transactional
    @Override
    public void delete(String resourceId) {
        Long id = employmentContractRepository.findIdByResourceId(resourceId).orElseThrow(
                () -> new ResourceNotFoundException("EmploymentContract with id = " + resourceId + " does not exist")
        );
        employmentContractRepository.deleteById(id);
    }
}
