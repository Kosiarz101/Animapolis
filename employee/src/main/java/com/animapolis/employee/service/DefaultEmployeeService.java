package com.animapolis.employee.service;

import com.animapolis.employee.exception.ResourceNotFoundException;
import com.animapolis.employee.mapper.EmployeeMapper;
import com.animapolis.employee.model.dto.request.EmployeeRequestDto;
import com.animapolis.employee.model.dto.response.EmployeeResponseDto;
import com.animapolis.employee.model.entity.Employee;
import com.animapolis.employee.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultEmployeeService extends BaseEntityService implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Transactional
    @Override
    public EmployeeResponseDto create(EmployeeRequestDto employeeDto) {
        Employee employee = employeeMapper.toEntity(employeeDto);

        super.prepareForCreation(employee);
        employeeRepository.save(employee);
        employeeRepository.refresh(employee);
        employee = employeeRepository.findById(employee.getId()).get();

        return employeeMapper.toDto(employee);
    }

    @Override
    public EmployeeResponseDto get(String resourceId) {
        Employee employee = employeeRepository.findByResourceId(resourceId).orElseThrow(
                () -> new ResourceNotFoundException("Employee with id = " + resourceId + " does not exist")
        );
        return employeeMapper.toDto(employee);
    }

    @Override
    public List<EmployeeResponseDto> getAll() {
        List<Employee> employees = employeeRepository.findAll();

        return employees.stream()
                .map(employeeMapper::toDto)
                .toList();
    }

    @Transactional
    @Override
    public EmployeeResponseDto update(String resourceId, EmployeeRequestDto employeeDto) {
        Long id = employeeRepository.findIdByResourceId(resourceId).orElseThrow(
                () -> new ResourceNotFoundException("Employee with id = " + resourceId + " does not exist")
        );
        Employee employee = employeeMapper.toEntity(employeeDto);
        employee.setId(id);
        super.prepareForUpdate(employee);

        employee = employeeRepository.saveAndFlush(employee);
        employeeRepository.refresh(employee);
        employee = employeeRepository.findById(id).get();
        return employeeMapper.toDto(employee);
    }

    @Transactional
    @Override
    public void delete(String resourceId) {
        Long id = employeeRepository.findIdByResourceId(resourceId).orElseThrow(
                () -> new ResourceNotFoundException("Employee with id = " + resourceId + " does not exist")
        );
        employeeRepository.deleteById(id);
    }
}
