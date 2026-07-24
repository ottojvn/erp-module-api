package com.ottojvn.erp_module_api.dto;

import java.util.UUID;
import java.math.BigDecimal;

public record EmployeeResponseDTO (
    UUID id,
    String name,
    String email,
    String department,
    BigDecimal salary
) {

}
