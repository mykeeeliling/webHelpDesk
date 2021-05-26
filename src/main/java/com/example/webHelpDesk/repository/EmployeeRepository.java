package com.example.webHelpDesk.repository;

import com.example.webHelpDesk.domain.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByEmployeeNumber(Long employeeNumber);
    Boolean existsByEmployeeNumber(Long employeeNumber);
}
