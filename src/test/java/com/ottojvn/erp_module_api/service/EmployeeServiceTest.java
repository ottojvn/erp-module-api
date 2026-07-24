package com.ottojvn.erp_module_api.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.ottojvn.erp_module_api.dto.EmployeeRequestDTO;
import com.ottojvn.erp_module_api.dto.EmployeeResponseDTO;
import com.ottojvn.erp_module_api.mapper.EmployeeMapper;
import com.ottojvn.erp_module_api.model.Employee;
import com.ottojvn.erp_module_api.repository.EmployeeRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    public void shouldSaveEmployeeSuccessfully() {
        EmployeeRequestDTO request = new EmployeeRequestDTO("John", "john@email.com", "Software Development", BigDecimal.valueOf(5000));
        Employee entity = new Employee(UUID.randomUUID(), "John", "john@email.com", "Software Development", BigDecimal.valueOf(5000));
        EmployeeResponseDTO response = new EmployeeResponseDTO(entity.getId(), "John", "john@email.com", "Software Development", BigDecimal.valueOf(5000));

        when(employeeMapper.toEntity(request)).thenReturn(entity);
        when(employeeRepository.save(entity)).thenReturn(entity);
        when(employeeMapper.toResponseDTO(entity)).thenReturn(response);

        EmployeeResponseDTO result = employeeService.save(request);
        assertThat(result).isNotNull().isEqualTo(response);
        verify(employeeRepository, times(1)).save(entity);
        verify(employeeMapper, times(1)).toEntity(request);
    }

    @Test
    public void shouldReturnListOfEmployees() {

        Employee entity1 = new Employee(UUID.randomUUID(), "Mary", "mary@email.com", "Software Development", BigDecimal.valueOf(5000));
        Employee entity2 = new Employee(UUID.randomUUID(), "John", "john@email.com", "Software Development", BigDecimal.valueOf(5000));

        EmployeeResponseDTO response1 = new EmployeeResponseDTO(entity1.getId(), "Mary", "mary@email.com", "Software Development", BigDecimal.valueOf(5000));
        EmployeeResponseDTO response2 = new EmployeeResponseDTO(entity2.getId(), "John", "john@email.com", "Software Development", BigDecimal.valueOf(5000));

        when(employeeMapper.toResponseDTO(entity1)).thenReturn(response1);
        when(employeeMapper.toResponseDTO(entity2)).thenReturn(response2);

        when(employeeRepository.findAll()).thenReturn(List.of(entity1, entity2));

        List<EmployeeResponseDTO> result = employeeService.findAll();

        assertThat(result)
            .isNotNull()
            .hasSize(2)
            .containsExactly(response1, response2);

        verify(employeeRepository, times(1)).findAll();

    }
}
