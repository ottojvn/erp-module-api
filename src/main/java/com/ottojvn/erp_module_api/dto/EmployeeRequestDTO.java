package com.ottojvn.erp_module_api.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record EmployeeRequestDTO (
    @NotBlank(message = "Employee must have a name")
    String name,

    @NotBlank(message = "Employee must have an email")
    @Email(message = "Invalid email")
    String email,

    @NotBlank(message = "Employee must have a department")
    String department,

    @NotNull(message = "Employee must have a salary")
    @PositiveOrZero(message = "Salary must be greater than or equal to zero")
    BigDecimal salary
) {

}
