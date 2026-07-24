package com.ottojvn.erp_module_api.controller;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ottojvn.erp_module_api.dto.EmployeeRequestDTO;
import com.ottojvn.erp_module_api.dto.EmployeeResponseDTO;
import com.ottojvn.erp_module_api.service.EmployeeService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(EmployeeController.class)
@AutoConfigureMockMvc(addFilters = false)
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void shouldCreateEmployee() throws Exception {
        EmployeeRequestDTO request = new EmployeeRequestDTO("Jane", "jane@email.com", "Software Development", BigDecimal.valueOf(5000));
        EmployeeResponseDTO response = new EmployeeResponseDTO(UUID.randomUUID(), "Jane", "jane@email.com", "Software Development", BigDecimal.valueOf(5000));

        when(employeeService.save(request)).thenReturn(response);

        mockMvc
            .perform(post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Jane"));
    }

    @Test
    public void shoudReturn400WhenInvalidInput() throws Exception {
        EmployeeRequestDTO request = new EmployeeRequestDTO("", "jane@email.com", "Software Development", BigDecimal.valueOf(5000));

        mockMvc
            .perform(post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.name").value("Employee must have a name"));
    }
}
