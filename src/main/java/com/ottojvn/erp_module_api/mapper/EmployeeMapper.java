package com.ottojvn.erp_module_api.mapper;

import com.ottojvn.erp_module_api.dto.EmployeeRequestDTO;
import com.ottojvn.erp_module_api.dto.EmployeeResponseDTO;
import com.ottojvn.erp_module_api.model.Employee;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    public Employee toEntity(EmployeeRequestDTO request);

    public EmployeeResponseDTO toResponseDTO(Employee entity);
}
