package com.ottojvn.erp_module_api.repository;

import java.util.UUID;

import com.ottojvn.erp_module_api.model.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {}
