package com.ottojvn.erp_module_api.service;

import com.ottojvn.erp_module_api.repository.EmployeeRepository;

import java.math.BigDecimal;
import java.util.List;

import com.ottojvn.erp_module_api.model.Employee;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public Employee save(Employee employee) {
        if(employee.getName() == null || employee.getName().isEmpty()) {
            throw new IllegalArgumentException("Employee name cannot be null or empty");
        }
        if(employee.getSalary().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Employee salary cannot be negative");
        }
        return employeeRepository.save(employee);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }
}
