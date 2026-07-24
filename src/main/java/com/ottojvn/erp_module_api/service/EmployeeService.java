package com.ottojvn.erp_module_api.service;

import com.ottojvn.erp_module_api.repository.EmployeeRepository;

import java.math.BigDecimal;
import java.util.List;

import com.ottojvn.erp_module_api.dto.EmployeeRequestDTO;
import com.ottojvn.erp_module_api.dto.EmployeeResponseDTO;
import com.ottojvn.erp_module_api.mapper.EmployeeMapper;
import com.ottojvn.erp_module_api.model.Employee;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeResponseDTO save(EmployeeRequestDTO request) {
        // if(employee.getName() == null || employee.getName().isEmpty()) {
        //     throw new IllegalArgumentException("Employee name cannot be null or empty");
        // }
        // if(employee.getSalary().compareTo(BigDecimal.ZERO) < 0) {
        //     throw new IllegalArgumentException("Employee salary cannot be negative");
        // }
        return employeeMapper.toResponseDTO(employeeRepository.save(employeeMapper.toEntity(request)));
    }

    public List<EmployeeResponseDTO> findAll() {
        return employeeRepository
            .findAll()
            .stream()
            .map(employeeMapper::toResponseDTO)
            .toList();
    }
}
