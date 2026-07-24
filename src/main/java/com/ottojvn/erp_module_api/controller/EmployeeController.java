package com.ottojvn.erp_module_api.controller;

import java.util.List;

import com.ottojvn.erp_module_api.dto.EmployeeRequestDTO;
import com.ottojvn.erp_module_api.dto.EmployeeResponseDTO;
import com.ottojvn.erp_module_api.service.EmployeeService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
   private final EmployeeService employeeService;

    @PostMapping
    public EmployeeResponseDTO createEmployee(@Valid @RequestBody EmployeeRequestDTO request) {
        return employeeService.save(request);
    }

    @GetMapping
    public List<EmployeeResponseDTO> getAllEmployees() {
        return employeeService.findAll();
    }
}
